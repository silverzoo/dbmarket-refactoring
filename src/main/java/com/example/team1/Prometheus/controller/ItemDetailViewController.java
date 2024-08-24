package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.Item;
import com.example.team1.Prometheus.entity.ItemDetailRequest;
import com.example.team1.Prometheus.entity.ItemDetailResponse;
import com.example.team1.Prometheus.service.ItemDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemDetailViewController {
    private final ItemDetailService itemDetailService;

    // NOTE : 상세 페이지로 이동
    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {
        ItemDetailResponse itemDetail = itemDetailService.findById(id);
        model.addAttribute("item", itemDetail);
        return "itemdetail/item";
    }

    // NOTE : 수정 페이지로 이동
    @GetMapping("/edit/{id}")
    public String updateItem(@PathVariable("id") Long id, Model model) {
        ItemDetailResponse itemDetail = itemDetailService.findById(id);
        model.addAttribute("item", itemDetail);
        return "itemdetail/edit";
    }

     // NOTE : 수정 후 상세페이지로 리다이렉트
     // Q : 뷰 컨트롤러에서도 API 컨트롤러에서도 updateItem 메서드가 호출되는데 한쪽에서만 해당 메서드가 처리되는 것이 맞는지?
    @PostMapping("/{id}")
    public String updateItem(@PathVariable("id") Long id, @ModelAttribute ItemDetailRequest request) {
        itemDetailService.updateItem(id, request);
        return "redirect:/items/" + id; // 수정 후 상세 페이지로 리다이렉트
    }


}
