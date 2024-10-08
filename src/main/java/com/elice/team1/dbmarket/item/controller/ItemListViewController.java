package com.elice.team1.dbmarket.item.controller;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.item.dto.ItemResponse;
import com.elice.team1.dbmarket.item.service.ItemListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemListViewController {
    private final ItemListService itemListService;

    @GetMapping("/category/{categoryId}")
    public String getCategoryItems(@PathVariable("categoryId") Category category,
                                   @RequestParam(value = "sorting-option", required = false) Integer sort,
                                   Model model) {

        log.info("sort 번호: {}", sort);

        List<ItemResponse> items = sortItem(category, sort);

        model.addAttribute("items", items);
        model.addAttribute("selectedSort", sort);

        return "item/items";
    }

    private List<ItemResponse> sortItem(Category category, Integer sort) {

        List<ItemResponse> items;

        if (sort != null) {
            if (sort == 1) {
                items = itemListService.getOrderByDateAsc(category);
            } else if (sort == 2) {
                items = itemListService.getOrderByDateDesc(category);
            } else {
                items = itemListService.getItemsByCategory(category);
            }
        } else {
            items = itemListService.getItemsByCategory(category);
        }
        return items;
    }

}
