package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemDetail;
import com.example.team1.Prometheus.service.ItemDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ItemDetailViewController {
    private final ItemDetailService itemDetailService;

    @GetMapping("/items/{id}")
    public String getItem(@PathVariable("id") Long id, Model model) {
        ItemDetail itemDetail = itemDetailService.findById(id);
        return "item";
    }

    // TODO : updateItem()
}
