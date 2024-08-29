package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.*;
import com.example.team1.Prometheus.exception.*;
import com.example.team1.Prometheus.mapper.CategoryMapper;
import com.example.team1.Prometheus.repository.CategoryRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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