package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.entity.UserDto;
import com.example.team1.Prometheus.repository.UserRepository;
import com.example.team1.Prometheus.service.UserFilter;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final UserFilter userFilter;
    private final UserRepository userRepository;

    private User userForRedirect;

    // 첫 홈 화면
    @GetMapping("/home")
    public String welcomeHome(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            return "redirect:/items";
        } else {
           userService.isSessionAvailable(model);
            return "/home";
        }
    }

    @GetMapping("/users/join")
    public String joinForm(Model model) {
        userService.isSessionAvailable(model);
        return "users/join";
    }

    @PostMapping("/users/join")
    public String CreateUser(UserDto form, @RequestParam("password_check") String password_check, HttpServletRequest httpServletRequest, Model model) {
        userService.isSessionAvailable(model);
        return userService.createUser(form, password_check, httpServletRequest);
    }

    @GetMapping("/users/login")
    public String login(Model model) {
        userService.isSessionAvailable(model);
        return "users/login";
    }

    @PostMapping("/users/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest httpServletRequest,Model model) {
        userService.isSessionAvailable(model);
        return userService.login(username, password, httpServletRequest);
    }

    @GetMapping("/users/logout")
    public String logout(HttpServletRequest httpServletRequest) {
        return userService.logout(httpServletRequest);
    }

    // 회원 상세 페이지
    @GetMapping("/users/{userid}")
    public String profile(@PathVariable Long userid, Model model) {
        userForRedirect = userService.findUserById(userid);
        return "redirect:/users/profile";

    }

    @GetMapping("/users/profile")
    public String profile(Model model) {
        User user = userForRedirect;
        model.addAttribute("userid", user.getUserId());
        model.addAttribute("username", user.getUserName());

        userService.getItemsByUserId(user.getUserId(), model);
        userForRedirect = null;
        return "users/profile";
    }

    // 마이페이지
    @GetMapping("/users/mypage")
    public String mypage(Model model) {
        User user = userFilter.findUserByFilter(model);
        userService.getItemsByUserId(user.getUserId(), model);

        return "users/mypage";

    }

    @DeleteMapping("/users/delete")
    public String deleteUser(Model model) {
        User user = userFilter.findUserByFilter(model);
        userService.deleteUser(user.getUserId());

        return "redirect:/users/logout";
    }


}
