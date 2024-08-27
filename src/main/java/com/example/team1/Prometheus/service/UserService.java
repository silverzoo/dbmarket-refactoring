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
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ItemDetailRepository itemDetailRepository;

    // 회원가입 DB 저장 로직
    public String createUser(UserDto form, String password_check,HttpServletRequest httpServletRequest) {
        User user = form.toEntity();
        User name = userRepository.findByUserName(form.getUsername());
        // id, password 가 비어있는지 확인
        if (user.getUserName().contains(" ") || user.getPassword().contains(" ") || user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            return "/users/join";
        }
        // 비밀번호 이중확인 로직
        // MD-5 암호화
        String password1 = Encrypt.md5(user.getPassword());
        String password2 = Encrypt.md5(password_check);
        if (!password1.equals(password2)) {
            return "/users/join";
        }
        // DB가 비어있을 때만 save()
        if (name == null) {
            userRepository.save(user);
            // 회원가입 성공시 세션 부여
            HttpSession httpSession = httpServletRequest.getSession(true);
            httpSession.setAttribute("user", user);
            return "redirect:/items";
            // 이미 존재하는 계정일 경우
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
        // MD-5 암호화
        String password1 = Encrypt.md5(password);
        String password2 = Encrypt.md5(user.getPassword());

        if (!password1.equals(password2)) {
            return "/users/login_retry";
        }
        // 로그인 성공시 세션 부여
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("user", user);

        return "redirect:/items";
    }

    public String logout(HttpServletRequest httpServletRequest) {
        // 세션이 이미 없는 경우
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            return "/home";
        }
        // 세션 제거
        else {
            HttpSession session = httpServletRequest.getSession(false);
            session.invalidate();
            return "/home";
        }
    }
    public User findUserById(Long userId){
        return userRepository.findByUserId(userId);
    }


    public String findUserName(Long userId) {
        User user = findUserById(userId);
        return user.getUserName();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }


    public Long getSession(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        return user.getUserId();
    }

    public void getItemsByUserId(Long userId, Model model) {
        User user = userRepository.findByUserId(userId);
        List<Item> items = itemDetailRepository.findAllByUserId(user.getUserId());
        model.addAttribute("items", items.stream().map(ItemListViewResponse::new).collect(Collectors.toList()));
    }
}


