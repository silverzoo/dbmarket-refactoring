package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemListViewResponse;
import com.example.team1.Prometheus.service.ItemListService;
import com.example.team1.Prometheus.service.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//아이템 목록 조회/표시
@Controller
@RequiredArgsConstructor
public class ItemListViewController {
    private final ItemListService itemListService;
    private final UserFilter userFilter;

    @GetMapping("/items")
    public String getAllItems(Model model) {


        List<ItemListViewResponse> items = itemListService.getAllItems();
        model.addAttribute("items",items);
        return "item/items";
    }



}
