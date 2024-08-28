package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.CategoryResponse;
import com.example.team1.Prometheus.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public String getAllCategories(Model model) {
        List<CategoryResponse> categories = categoryService.getAll();
        model.addAttribute("categories", categories);

        return "category/categories";
    }
}
