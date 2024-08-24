package com.example.team1.Prometheus.controller;


import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.entity.UserDto;
import com.example.team1.Prometheus.repository.UserRepository;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class UserController {
    private final UserService userService;
    private final HttpServletRequest httpServletRequest;

    @Autowired
    public UserController(UserService userService, HttpServletRequest httpServletRequest) {
        this.userService = userService;
        this.httpServletRequest = httpServletRequest;
    }

    // 첫 홈 화면
    @GetMapping("/home")
    public String welcomeHome(Model model, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("user") != null) {
            return "redirect:/items";
        }
        else {
            return "/home";
        }
    }

    @GetMapping("/users/join")
    public String joinForm() {
        return "users/join";
    }

    @PostMapping("/users/join")
    public String CreateUser(UserDto form) {
        return userService.createUser(form, httpServletRequest);
    }

    @GetMapping("/users/login")
    public String login() {
        return "users/login";
    }

    @PostMapping("/users/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {
        return userService.login(username, password, httpServletRequest);
    }

    @GetMapping("/users/logout")
    public String logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return userService.logout(httpServletRequest,httpServletResponse);
    }

    // 회원 상세 페이지
    @GetMapping("/users/{userid}")
    public String profile(@PathVariable Long userid, Model model) {
        String userName = userService.findUserName(userid);
        model.addAttribute("userid", userid);
        model.addAttribute("username", userName);

        //TODO 회원 판매 상품 리스트 구현 해야함
        //Item item = itemRepository.findAllById(id); ??
        return "users/profile";
    }


}
