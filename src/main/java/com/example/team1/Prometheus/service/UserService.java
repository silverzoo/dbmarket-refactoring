package com.example.team1.Prometheus.service;

import com.example.team1.Prometheus.entity.*;
import com.example.team1.Prometheus.repository.CommentRepository;
import com.example.team1.Prometheus.repository.ItemDetailRepository;
import com.example.team1.Prometheus.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ItemDetailRepository itemDetailRepository;
    private final CommentRepository commentRepository;

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
            return "redirect:/users/join?error=already_exists";
        }
    }

    // 로그인 로직
    public String login(String username, String password, HttpServletRequest httpServletRequest) {
        // 입력한 username-password 를 모두 충족하지 못하면 null
        User user = userRepository.findByUserNameAndPassword(username, password);

        // 로그인 검증 로직
        if (user == null) {
            return "redirect:/users/login?error= not_exist";
        }
        // MD-5 암호화
        String password1 = Encrypt.md5(password);
        String password2 = Encrypt.md5(user.getPassword());

        if (!password1.equals(password2)) {
            return "redirect:/users/login?error=wrong_password";
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



    public User getSessionUser(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        return (User) session.getAttribute("user");
    }


    public void getItemsByUserId(Long userId, Model model) {
        User user = userRepository.findByUserId(userId);
        List<Item> items = itemDetailRepository.findAllByUserId(user.getUserId());
        model.addAttribute("items", items.stream().map(ItemListViewResponse::new).collect(Collectors.toList()));
    }

    public void isSessionAvailable(Model model) {
        model.addAttribute("isSessionAvailable","false");
    }

    public void deleteUser(Long userId){
        User user = userRepository.findByUserId(userId);
        // 작성한 판매글 List 삭제
        List<Item> items = itemDetailRepository.findAllByUserId(user.getUserId());
        for (Item item : items) {
            log.info("\n회원이 작성한 게시글 : {} 삭제 \n", item.getName());
            itemDetailRepository.delete(item);
        }

        // 탈퇴한 회원이 작성한 코멘트 삭제
        List<Comment> comments = commentRepository.findAllByUser_UserId(user.getUserId());
        for (Comment comment : comments) {
            log.info("\n회원이 작성한 코멘트 : {} 삭제 \n", comment.getContent());
            commentRepository.delete(comment);
        }

        // 회원에게 작성된 코멘트 삭제
        List<Comment> comments1 = commentRepository.findAllByReviewerName(user.getUserName());
        for (Comment comment : comments1) {
            log.info("\n회원에게 작성된 코멘트 : {} 삭제 \n", comment.getContent());
            commentRepository.delete(comment);
        }

        userRepository.delete(user);
        log.info("\n회원 삭제 : {} 삭제 \n", user.getUserName());
    }
}


