package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemListViewResponse;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.service.ItemListService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//아이템 목록 조회/표시
@Controller
@RequiredArgsConstructor
public class ItemListViewController {
    private final ItemListService itemListService;

    @GetMapping("/items")
    public String getAllItems(Model model, HttpServletRequest httpServletRequest) {


        List<ItemListViewResponse> items = itemListService.getAllItems();
        model.addAttribute("items",items);
        return "items";
    }



}
