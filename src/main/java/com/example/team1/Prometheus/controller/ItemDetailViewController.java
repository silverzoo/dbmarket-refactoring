package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemDeleteResponse;
import com.example.team1.Prometheus.entity.ItemModifyRequest;
import com.example.team1.Prometheus.entity.ItemModifyResponse;
import com.example.team1.Prometheus.entity.ItemResponse;
import com.example.team1.Prometheus.service.ItemDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    // NOTE : 상세 페이지로 이동
    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {


        ItemResponse item = itemDetailService.viewItem(id);
        model.addAttribute("item", item);

        log.info("\n\n아이템 확인: {}\n\n", item);

        return "itemdetail/item";
    }

    // NOTE : 수정 페이지로 이동
    @GetMapping("/edit/{id}")
    public String updateItem(@PathVariable("id") Long id, Model model, HttpServletRequest httpServletRequest) {

        String refer = httpServletRequest.getHeader("referer");
        log.info("\n\n상세 조회에서 세션 헤더 정보: {}\n\n", refer);

        ItemResponse item = itemDetailService.findById(id, httpServletRequest);
        model.addAttribute("item", item);

        return "itemdetail/edit";
    }

    // NOTE : 수정 후 상세페이지로 리다이렉트
    @PostMapping("/{id}")
    public String updateItem(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, @Valid @ModelAttribute ItemModifyRequest request, BindingResult bindingResult) {

//        // 모든 에러 처리
//        if(bindingResult.hasErrors()) {
//            log.info("\n\n수정 실패\n\n");
//            return "/item/" + id;
//        }

        ItemModifyResponse res = itemDetailService.updateItem(id, request);
        log.info("\n\n상품 수정 확인: {}\n\n", res);

        redirectAttributes.addFlashAttribute("success", "수정되었습니다.");
        return "redirect:/items/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable("id") Long id, RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {

        ItemDeleteResponse res = itemDetailService.deleteItem(id, httpServletRequest);
        log.info("\n\n상품 삭제 확인: {}\n\n", res);

        redirectAttributes.addFlashAttribute("success", "삭제되었습니다.");
        return "redirect:/items";
    }

    // TODO: 판매자 정보페이지에서 삭제 시, 판매자 정보 뷰로 돌아가게 하기
//    @RequestMapping("/redirectAfterDelete")
//    public String redirectAfterDelete() {
//        return "items";
//    }

}
