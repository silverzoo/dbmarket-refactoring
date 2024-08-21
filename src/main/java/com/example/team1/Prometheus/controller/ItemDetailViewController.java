package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemDetail;
import com.example.team1.Prometheus.entity.ItemDetailRequest;
import com.example.team1.Prometheus.entity.ItemDetailResponse;
import com.example.team1.Prometheus.service.ItemDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemDetailViewController {
    private final ItemDetailService itemDetailService;

    // NOTE : 상세 페이지로 이동
    // Q : 뷰 반환컨트롤러 메서드에서도 req,res DTO객체들을 활용해서 로직을 짜야하는 것인지?
    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {
        ItemDetailResponse itemDetail = itemDetailService.findById(id);
        model.addAttribute("item", itemDetail);
        return "itemdetail/item";
    }

    // NOTE : 수정 페이지로 이동
    @GetMapping("{id}/edit")
    public String updateItem(@PathVariable("id") Long id, Model model) {
        ItemDetailResponse itemDetail = itemDetailService.findById(id);
        model.addAttribute("item", itemDetail);
        return "itemdetail/edit";
    }

     // NOTE : 수정 후 상세페이지로 리다이렉트
    @PostMapping("/{id}")
    public String updateItem(@PathVariable("id") Long id, @ModelAttribute ItemDetailRequest request) {
        itemDetailService.updateItem(id, request);
        return "redirect:/items/" + id; // 수정 후 상세 페이지로 리다이렉트
    }
}
