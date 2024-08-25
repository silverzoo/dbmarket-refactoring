package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.Item;
import com.example.team1.Prometheus.entity.ItemListViewResponse;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.entity.UserDto;
import com.example.team1.Prometheus.repository.ItemDetailRepository;
import com.example.team1.Prometheus.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserRepository userRepository;
    private final ItemDetailRepository itemDetailRepository;

    // 회원가입 DB 저장 로직
    public String createUser(UserDto form, String password_check, HttpServletRequest httpServletRequest) {

        User user = form.toEntity();
        User name = userRepository.findByUserName(form.getUsername());
        // 비밀번호 이중확인 로직
        if (!user.getPassword().equals(password_check)) {
            return "/users/join_wrongpassword";
        }
        if (name == null) {
            userRepository.save(user);
            //return "index";

            HttpSession httpSession = httpServletRequest.getSession(true);
            httpSession.setAttribute("user", user);
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
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("user", user);

        //return "index";

        return "redirect:/items";
    }

    public String logout(HttpServletRequest httpServletRequest) {
        if(httpServletRequest.getSession().getAttribute("user") == null) {
        return "/home";
        }
        else {
            HttpSession session = httpServletRequest.getSession(false);
            session.invalidate();
            return "/home";
        }
    }

    public String findUserName(Long userId) {
        return userRepository.findByUserId(userId).getUserName();
    }

    public Long getSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        return user.getUserId();
    }

    public List<ItemListViewResponse> getItemsByUserId(Long userId){
        User user = userRepository.findByUserId(userId);
        List<Item> items = itemDetailRepository.findAllByUserId(user.getUserId());
        return items.stream()
                .map(ItemListViewResponse::new)
                .collect(Collectors.toList());
    }



}


