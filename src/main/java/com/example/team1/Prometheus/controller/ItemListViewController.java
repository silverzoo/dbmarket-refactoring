package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.ItemListViewResponse;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.service.ItemListService;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final UserService userService;

    @GetMapping("/items")
    public String getAllItems(Model model, HttpServletRequest httpServletRequest) {

        // 마이페이지로 넘어가기 위한 메서드 (로그인 세션 필요)
        User user = userService.getSessionUser(httpServletRequest);
        model.addAttribute("username", user.getUserName());
        model.addAttribute("myuserid", user.getUserId());
        // 로그인 세션이 없다면 마이페이지 버튼을 숨기도록?

        List<ItemListViewResponse> items = itemListService.getAllItems();
        model.addAttribute("items",items);
        return "items";
    }



}
