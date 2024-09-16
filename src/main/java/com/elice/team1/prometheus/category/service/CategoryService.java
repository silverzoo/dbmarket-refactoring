package com.elice.team1.prometheus.category.service;

import com.elice.team1.prometheus.category.dto.CategoryRequest;
import com.elice.team1.prometheus.category.dto.CategoryResponse;
import com.elice.team1.prometheus.category.entity.Category;
import com.elice.team1.prometheus.common.exception.NotFoundCategoryById;
import com.elice.team1.prometheus.common.exception.UnauthorizedCreateByUser;
import com.elice.team1.prometheus.common.exception.UnauthorizedDeleteByUser;
import com.elice.team1.prometheus.common.exception.UnauthorizedModifyByUser;
import com.elice.team1.prometheus.category.mapper.CategoryMapper;
import com.elice.team1.prometheus.category.repository.CategoryRepository;
import com.elice.team1.prometheus.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final CategoryMapper categoryMapper;
    private final CategoryMappingService categoryMappingService;

    @Transactional
    public List<CategoryResponse> getAll() {

        List<Category> categories = categoryRepository.findAll();

        return categories.stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CategoryResponse createCategory(CategoryRequest categoryRequest, HttpServletRequest httpServletRequest) {
        String userName = userService.getSessionUser(httpServletRequest).getUserName();

        if (!userName.equals("admin")) {
            throw new UnauthorizedCreateByUser(userName);
        }

        Category category = categoryMapper.toEntity(categoryRequest);

        Category savedCategory = categoryRepository.save(category);


        return categoryMapper.toResponse(savedCategory);
    }

    @Transactional
    public CategoryResponse findById(Long id, HttpServletRequest httpServletRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundCategoryById(id));

        String userName = userService.getSessionUser(httpServletRequest).getUserName();

        if(!userName.equals("admin")) {
            throw new UnauthorizedModifyByUser(userName);
        }

        return categoryMapper.toResponse(category);
    }

    @Transactional
    public CategoryResponse updateItem(Long id, CategoryRequest request, HttpServletRequest httpServletRequest) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundCategoryById(id));

        String userName = userService.getSessionUser(httpServletRequest).getUserName();

        if (!userName.equals("admin")) {
            throw new UnauthorizedDeleteByUser(userName);
        }

        Category updatedCategory = Category.builder()
                .categoryId(category.getCategoryId())
                .name(request.getName())
                .createdAt(category.getCreatedAt())
                .build();

        Category savedCategory = categoryRepository.save(updatedCategory);

        return categoryMapper.toResponse(savedCategory);
    }

    @Transactional
    public CategoryResponse deleteItem(Long id, HttpServletRequest httpServletRequest) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundCategoryById(id));

        String userName = userService.getSessionUser(httpServletRequest).getUserName();

        if (!userName.equals("admin")) {
            throw new UnauthorizedDeleteByUser(userName);
        }

        categoryRepository.deleteById(id);

        return categoryMapper.toResponse(category);
    }

}