package com.example.team1.Prometheus.controller;


import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.entity.UserForm;
import com.example.team1.Prometheus.repository.UserRepository;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 첫 홈 화면 -> 로그인 이후에는 Index가 홈 화면
    @GetMapping("/home")
    public String welcomeHome(Model model) {
        return "home";
    }

    // Index (TestController 기능 이동)
    @GetMapping("/")
    public String thymeleafExample(Model model) {
        return "index";
    }

    @GetMapping("/users/join")
    public String joinForm() {
        return "users/join";
    }

    @PostMapping("/users/join")
    public String CreateUser(UserForm form) {
        userService.createUser(form);
        return "index";
    }

    @GetMapping("/users/login")
    public String login() {
        return "users/login";
    }


    @PostMapping("/users/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {
        return userService.login(username, password, httpServletRequest);
    }
}
