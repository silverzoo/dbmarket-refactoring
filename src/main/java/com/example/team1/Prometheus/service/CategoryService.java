package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Category;
import com.example.team1.Prometheus.entity.CategoryRequest;
import com.example.team1.Prometheus.entity.CategoryResponse;
import com.example.team1.Prometheus.exception.NotFoundItemById;
import com.example.team1.Prometheus.exception.UnauthorizedCreateByUser;
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
    public CategoryResponse createCategory(CategoryRequest categoryRequest, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String userName = userService.getSessionUser(httpServletRequest, httpServletResponse).getUserName();

        if (!userName.equals("admin")) {
            throw new UnauthorizedCreateByUser(userName);
        }

        Category category = categoryMapper.toEntity(categoryRequest);

        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }
}