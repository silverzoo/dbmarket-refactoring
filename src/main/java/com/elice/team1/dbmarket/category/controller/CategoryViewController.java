package com.elice.team1.dbmarket.category.controller;

import com.elice.team1.dbmarket.category.dto.CategoryRequest;
import com.elice.team1.dbmarket.category.dto.CategoryResponse;
import com.elice.team1.dbmarket.category.service.CategoryService;
import com.elice.team1.dbmarket.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryViewController {

    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping()
    public String getAllCategories(Model model, HttpServletRequest httpServletRequest) {

        List<CategoryResponse> categories = categoryService.getAll();

        String userName = userService.getSessionUser(httpServletRequest).getUsername();
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
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest httpServletRequest) {

        CategoryResponse category = categoryService.createCategory(categoryRequest, httpServletRequest);
        log.info("\n\n등록할 카테고리 확인: {}\n\n", category);

        redirectAttributes.addFlashAttribute("success", "등록되었습니다.");
        return "redirect:/categories";
    }

    @GetMapping("/{id}")
    public String updateCategory(@PathVariable("id") Long id, Model model,
                                 HttpServletRequest httpServletRequest) {

        CategoryResponse category = categoryService.findById(id, httpServletRequest);

        model.addAttribute("category", category);

        return "category/edit";
    }

    @PostMapping("/{id}")
    public String updateCategory(@PathVariable("id") Long id,
                                 CategoryRequest request,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest httpServletRequest) {

        CategoryResponse res = categoryService.updateItem(id, request, httpServletRequest);
        log.info("\n\n상품 수정 확인: {}\n\n", res);

        redirectAttributes.addFlashAttribute("success", "수정되었습니다.");
        return "redirect:/categories";
    }

    @DeleteMapping()
    public String deleteCategory(@RequestParam("categoryId") Long id,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest httpServletRequest) {

        CategoryResponse res = categoryService.deleteItem(id, httpServletRequest);
        log.info("\n\n상품 삭제 확인: {}\n\n", res);

        redirectAttributes.addFlashAttribute("success", "삭제되었습니다.");
        return "redirect:/categories";
    }
}
