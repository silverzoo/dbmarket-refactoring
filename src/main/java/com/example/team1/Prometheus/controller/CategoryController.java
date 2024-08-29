package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.CategoryRequest;
import com.example.team1.Prometheus.entity.CategoryResponse;
import com.example.team1.Prometheus.service.CategoryService;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping()
    public String getAllCategories(Model model, HttpServletRequest httpServletRequest) {

        List<CategoryResponse> categories = categoryService.getAll();

        String userName = userService.getSessionUser(httpServletRequest).getUserName();
        log.info("\n\n현재 세션 유저네임 확인: {}\n\n", userName);

        model.addAttribute("userName", userName);
        model.addAttribute("categories", categories);

        return "category/categories";
    }

    @GetMapping("/new")
    public String createCategory() {

        return "category/create";
    }

    @PostMapping("/new")
    public String createCategory(@ModelAttribute("categoryRequest") CategoryRequest categoryRequest,
                                 HttpServletRequest httpServletRequest){
        CategoryResponse category = categoryService.createCategory(categoryRequest, httpServletRequest);
        log.info("\n\n등록할 카테고리 확인: {}\n\n", category);

        return "redirect:/categories";
    }

    @PutMapping()
    public String updateCategory() {
        return "";
    }

    @DeleteMapping()
    public String deleteCategory() {
        return "";
    }
}
