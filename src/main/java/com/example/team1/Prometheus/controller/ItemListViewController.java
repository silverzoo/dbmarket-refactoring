package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemResponse;
import com.example.team1.Prometheus.service.ItemListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//아이템 목록 조회/표시
@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemListViewController {
    private final ItemListService itemListService;

    @GetMapping("/category/{categoryId}")
    public String getCategoryItems(@PathVariable("categoryId") Long categoryId,
                                   @RequestParam(value = "sorting-option", required = false) Integer sort,
                                   Model model) {

        log.info("sort 번호: {}", sort);

        List<ItemResponse> items = sortItem(categoryId, sort);

        model.addAttribute("items", items);
        model.addAttribute("selectedSort", sort);

        return "item/items";
    }

    private List<ItemResponse> sortItem(Long categoryId, Integer sort) {

        List<ItemResponse> items;

        if (sort != null) {
            if (sort == 1) {
                items = itemListService.getOrderByDateAsc(categoryId);
            } else if (sort == 2) {
                items = itemListService.getOrderByDateDesc(categoryId);
            } else {
                items = itemListService.getItemsByCategory(categoryId);
            }
        } else {
            items = itemListService.getItemsByCategory(categoryId);
        }
        return items;
    }




//    @GetMapping("/category/{categoryId}")
//    public String getAllItems(@PathVariable("categoryId") Long categoryId, Model model) {
//        List<ItemResponse> items = itemListService.getItemsByCategory(categoryId);
//        model.addAttribute("items",items);
//        return "item/items";
//    }

//    @GetMapping("/sorting/{categoryId}")
//    public String getSortItems(Model model, @PathVariable("categoryId") Long categoryId,
//                               @RequestParam("sorting-option") int sort) {
//        List<ItemResponse> items = itemListService.getItemsByCategory(categoryId);
//
//        log.info("sortValue={}", sort);
//
////        if(sort == 1){
////            items = itemListService.getOrderByDateAsc(categoryId);
////        }
////        if(sort == 2){
////            items = itemListService.getOrderByDateDesc(categoryId);
////        }
//
//        model.addAttribute("items",items);
//
//        return "redirect:/category/" + categoryId + "?sorting-option=" + sort;
//    }

}
