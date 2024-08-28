package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.CategoryRequest;
import com.example.team1.Prometheus.entity.CategoryResponse;
import com.example.team1.Prometheus.entity.ItemResponse;
import com.example.team1.Prometheus.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/edit")
    public String createCategory() {

        return "category/edit";
    }

    @PostMapping("/edit")
    public String createCategory(@ModelAttribute("categoryRequest") CategoryRequest categoryRequest) {

        return "category/edit";
    }
}
