package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemListViewResponse;
import com.example.team1.Prometheus.entity.ItemResponse;
import com.example.team1.Prometheus.service.ItemListService;
import com.example.team1.Prometheus.service.UserFilter;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//아이템 목록 조회/표시
@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemListViewController {
    private static final Logger log = LoggerFactory.getLogger(ItemListViewController.class);
    private final ItemListService itemListService;
    private final UserFilter userFilter;

    @GetMapping("/category/{categoryId}")
    public String getAllItems(@PathVariable("categoryId") Long categoryId, Model model) {
        List<ItemResponse> items = itemListService.getItemsByCategory(categoryId);
        model.addAttribute("items",items);
        return "item/items";
    }

    @GetMapping("/sorting/{id}")
    public String getSortItems(Model model, @PathVariable("id") Long id) {
        List<ItemListViewResponse> items = itemListService.getAllItems();
        log.info("sortValue={}", 1);
        if(1 == 1){
            items = itemListService.getOrderByDateAsc();
        }
        if(1 == 2){
            items = itemListService.getOrderByDateDesc();
        }
        model.addAttribute("items",items);
        return "redirect:/category/" + id;
    }

}
