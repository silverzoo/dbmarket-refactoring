package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.entity.UserDto;
import com.example.team1.Prometheus.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입 DB 저장 로직
    public String createUser(UserDto form) {
        User user = form.toEntity();
        // 아이디 중복검사 로직
        User name = userRepository.findByUserName(form.getUsername());
        if (name == null) {
            userRepository.save(user);
            //return "index";
            return "redirect:/items";
        } else {
            return "/users/join_retry";
        }
    }

    // 로그인 로직
    public String login(String username, String password, HttpServletRequest httpServletRequest) {
        // 입력한 username-password 를 모두 충족하지 못하면 null
        User user = userRepository.findByUserNameAndPassword(username, password);
        // 로그인 검증 로직
        if (user == null) {
            return "/users/login_retry";
        }
        // 로그인세션 부여
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("user", user);
        //return "index";

        return "redirect:/items";

    }
}
