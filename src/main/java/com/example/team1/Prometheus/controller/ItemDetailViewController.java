package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemDeleteResponse;
import com.example.team1.Prometheus.entity.ItemModifyRequest;
import com.example.team1.Prometheus.entity.ItemModifyResponse;
import com.example.team1.Prometheus.entity.ItemResponse;
import com.example.team1.Prometheus.service.ItemDetailService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemDetailViewController {
    private final ItemDetailService itemDetailService;

    // NOTE : 상세 페이지로 이동
    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {


        ItemResponse item = itemDetailService.viewIteam(id);
        model.addAttribute("item", item);

        log.info("\n\n아이템 확인: {}\n\n", item);

        return "itemdetail/item";
    }

    // NOTE : 수정 페이지로 이동
    @GetMapping("/edit/{id}")
    public String updateItem(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        try {
            String refer = httpServletRequest.getHeader("referer");
            log.info("\n\n상세 조회에서 세션 헤더 정보: {}\n\n", refer);

            ItemResponse item = itemDetailService.findById(id, httpServletRequest);
            model.addAttribute("item", item);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/items";
        }

        return "itemdetail/edit";
    }

    // NOTE : 수정 후 상세페이지로 리다이렉트
    @PostMapping("/{id}")
    public String updateItem(@PathVariable("id") Long id, @ModelAttribute ItemModifyRequest request) {

        ItemModifyResponse res = itemDetailService.updateItem(id, request);

        log.info("\n\n상품 수정 확인: {}\n\n", res);

        return "redirect:/items/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        try {
            ItemDeleteResponse res = itemDetailService.deleteItem(id, httpServletRequest);
            log.info("\n\n상품 삭제 확인: {}\n\n", res);

            redirectAttributes.addFlashAttribute("message", "삭제되었습니다.");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/items";
    }

    // TODO: 판매자 정보페이지에서 삭제 시, 판매자 정보 뷰로 돌아가게 하기
//    @RequestMapping("/redirectAfterDelete")
//    public String redirectAfterDelete() {
//        return "items";
//    }

}
