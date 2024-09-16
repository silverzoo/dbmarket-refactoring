package com.elice.team1.prometheus.item.controller;

import com.elice.team1.prometheus.item.dto.ItemDeleteResponse;
import com.elice.team1.prometheus.item.dto.ItemPostDto;
import com.elice.team1.prometheus.item.dto.ItemResponse;
import com.elice.team1.prometheus.common.exception.ImageUploadException;
import com.elice.team1.prometheus.item.service.ItemDetailService;
import com.elice.team1.prometheus.item.service.RegisterItemService;
import com.elice.team1.prometheus.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Validated
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemDetailViewController {
    private final ItemDetailService itemDetailService;
    private final UserService userService;
    private final RegisterItemService registerItemService;

    // NOTE : 상세 페이지로 이동
    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") Long id, Model model, HttpServletRequest httpServletRequest) {

        ItemResponse item = itemDetailService.viewItem(id);
        log.info("\n\n아이템 확인: {}\n\n", item);

        Long userId = userService.getSessionUser(httpServletRequest).getUserId();
        log.info("\n\n현재 세션 아이디 확인: {}\n\n", userId);

        String username = userService.findUserById(item.getUserId()).getUserName();
        model.addAttribute("username", username);

        model.addAttribute("userId", userId);
        model.addAttribute("item", item);

        return "item/detail";
    }

    // NOTE : 수정 페이지로 이동
    @GetMapping("/edit/{id}")
    public String updateItem(@PathVariable("id") Long id, Model model, HttpServletRequest httpServletRequest) {

        ItemResponse item = itemDetailService.findById(id, httpServletRequest);
        model.addAttribute("item", item);

        return "item/edit";
    }

    // NOTE : 수정 후 상세페이지로 리다이렉트
    @PostMapping("/{id}")
    public String updateItem(@PathVariable("id") Long id,
                             RedirectAttributes redirectAttributes,
                             @ModelAttribute("itemPostDto") @Valid ItemPostDto itemPostDto) throws ImageUploadException {

        registerItemService.updateItemToDb(id, itemPostDto);

        redirectAttributes.addFlashAttribute("success", "수정되었습니다.");
        return "redirect:/items/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        ItemDeleteResponse res = itemDetailService.deleteItem(id, httpServletRequest);
        log.info("\n\n상품 삭제 확인: {}\n\n", res);

        redirectAttributes.addFlashAttribute("success", "삭제되었습니다.");
        return "redirect:/categories";
    }

}
