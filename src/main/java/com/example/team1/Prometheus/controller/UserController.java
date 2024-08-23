package com.example.team1.Prometheus.controller;


import com.example.team1.Prometheus.entity.UserDto;
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
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 첫 홈 화면 -> 로그인 이후에는 Index가 홈 화면
    @GetMapping("/home")
    public String welcomeHome(Model model, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();

        System.out.println(session.getAttribute("user"));

        if (session.getAttribute("user") != null) {
            return "redirect:/items";
        }
        else {
            return "/home";
        }
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
    public String CreateUser(UserDto form, String username, String password) {
        return userService.createUser(form, httpServletRequest);
        // 아이디 중복시 : "users/join_retry" 로 이동
        // 회원가입 성공시 : "/index" 로 이동
    }

    @GetMapping("/users/login")
    public String login() {
        return "users/login";
    }

    @PostMapping("/users/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest httpServletRequest) {
        return userService.login(username, password, httpServletRequest);
        // 로그인 실패시 : "users/login_retry" 로 이동
        // 로그인 성공시 : "/index" 로 이동
    }

    // WIP
    // 판매자 프로필 페이지
    @GetMapping("/users/{username}")
    public String profile(@PathVariable String username,  Model model) {
        // 1. 회원 정보 매개변수 전송
        model.addAttribute("username", username);

        // 2. 회원 판매 상품 리스트
        //   Item item = itemRepository.findAllById(id);

        // 3. 회원 코멘트로 이동하는 버튼


        return "users/profile";
    }


}
