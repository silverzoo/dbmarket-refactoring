package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemModifyRequest;
import com.example.team1.Prometheus.entity.ItemModifyResponse;
import com.example.team1.Prometheus.entity.ItemResponse;
import com.example.team1.Prometheus.service.ItemDetailService;
import com.example.team1.Prometheus.service.UserFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemDetailViewController {
    private final ItemDetailService itemDetailService;
    private final UserFilter userFilter;

    // NOTE : 상세 페이지로 이동
    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {

        // 세션
        userFilter.findUserByFilter(model);

        ItemResponse item = itemDetailService.findById(id);
        model.addAttribute("item", item);
        log.info("\n\n 유저아이디 정보 확인: " + item.getUserId() + "\n\n");

        return "itemdetail/item";
    }

    // NOTE : 수정 페이지로 이동
    @GetMapping("/edit/{id}")
    public String updateItem(@PathVariable("id") Long id, Model model, HttpServletRequest req) {

        // 세션
        userFilter.findUserByFilter(model);
        String refer = userFilter.getHeader(req);
        log.info(STR."\n\n상세 조회에서 세션 헤더 정보: \{refer}\n\n");

        ItemResponse item = itemDetailService.findById(id);
        model.addAttribute("item", item);
        return "itemdetail/edit";
    }

    // NOTE : 수정 후 상세페이지로 리다이렉트
    @PostMapping("/{id}")
    public String updateItem(@PathVariable("id") Long id, @ModelAttribute ItemModifyRequest request) {

        // FIXME: 뷰에 값은 잘 전송되는데 로그로는 왜 null만 뜨는지?
        log.info("\n\n상품 수정 되었나요?: {}\n\n", itemDetailService.updateItem(id, request));
        return "redirect:/items/" + id;
    }




    // TODO: 판매자 정보페이지에서 삭제 시, 판매자 정보 뷰로 돌아가게 하기
}
