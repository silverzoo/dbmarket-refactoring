package com.elice.team1.prometheus.category.controller;

import com.elice.team1.prometheus.category.dto.CategoryResponse;
import com.elice.team1.prometheus.category.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {

    private CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getCategories() {
        log.info("\n\n\n" + categoryService.getAll() +"\n\n");
        return categoryService.getAll();
    }
}
