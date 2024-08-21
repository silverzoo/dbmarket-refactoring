package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.entity.UserForm;
import com.example.team1.Prometheus.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.HandshakeCompletedEvent;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원가입 DB 저장 로직
    public User createUser(UserForm form) {
        User user = form.toEntity();
        return userRepository.save(user);
    }

    // 로그인 확인 로직
    public String login(String username, String password, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByUserNameAndPassword(username, password);
        if (user == null) {
            return "/users/login_retry";
        }
        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("user", user);
        return "index";
    }
}
