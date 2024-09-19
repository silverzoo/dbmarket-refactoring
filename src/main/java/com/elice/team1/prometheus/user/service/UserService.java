package com.elice.team1.prometheus.user.service;

import com.elice.team1.prometheus.comment.entity.Comment;
import com.elice.team1.prometheus.common.util.Encrypt;
import com.elice.team1.prometheus.item.dto.ItemListViewResponse;
import com.elice.team1.prometheus.item.entity.Item;
import com.elice.team1.prometheus.user.dto.UserDto;
import com.elice.team1.prometheus.user.entity.User;
import com.elice.team1.prometheus.comment.repository.CommentRepository;
import com.elice.team1.prometheus.item.repository.ItemDetailRepository;
import com.elice.team1.prometheus.user.repository.UserRepository;
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

    public String createUser(UserDto form, String password_check, HttpServletRequest httpServletRequest) {
        User user = form.toEntity();
        User name = userRepository.findByUserName(form.getUsername());

        if (user.getUserName().contains(" ") || user.getPassword().contains(" ") || user.getUserName().isEmpty() || user.getPassword().isEmpty()) {
            return "users/join";
        }
        String password1 = Encrypt.md5(user.getPassword());
        String password2 = Encrypt.md5(password_check);
        if (!password1.equals(password2)) {
            log.info("회원가입 실패 : 비밀번호 불일치");
            return "users/join";
        }

        if (name == null) {
            userRepository.save(user);
            log.info("회원가입 성공, 세션 부여");
            HttpSession httpSession = httpServletRequest.getSession(true);
            httpSession.setAttribute("user", user);
            log.info("계정 DB저장 성공");
            return "redirect:/categories";

        } else {
            log.info("회원가입 실패 : 이미 존재하는 계정");
            return "redirect:/users/join?error=already_exists";
        }
    }

    // 로그인 로직
    public String login(String username, String password, HttpServletRequest httpServletRequest) {
        // 입력한 username-password 를 모두 충족하지 못하면 null
        User user = userRepository.findByUserNameAndPassword(username, password);

        // 로그인 검증 로직
        if (user == null) {
            log.info("로그인 실패 : 존재하지 않는 계정");
            return "redirect:/users/login?error= not_exist";
        }
        String password1 = Encrypt.md5(password);
        String password2 = Encrypt.md5(user.getPassword());

        if (!password1.equals(password2)) {
            log.info("로그인 실패 : 틀린 비밀번호");
            return "redirect:/users/login?error=wrong_password";
        }
        log.info("로그인 성공, 세션 부여");
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("user", user);

        return "redirect:/categories";
    }

    public String logout(HttpServletRequest httpServletRequest, Model model) {
        if (httpServletRequest.getSession().getAttribute("user") == null) {
            log.info("이미 세션이 없으므로 home으로 리다이렉트");
            return "home";
        } else {
            log.info("세션제거, home으로 리다이렉트");
            HttpSession session = httpServletRequest.getSession(false);
            session.invalidate();
            return "home";
        }
    }

    public User findUserById(Long userId) {
        User user = userRepository.findByUserId(userId);
        return user;
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
        User user = (User) session.getAttribute("user");
        return user;
    }


    public void getItemsByUserId(Long userId, Model model) {
        User user = userRepository.findByUserId(userId);
        List<Item> items = itemDetailRepository.findAllByUser(user);
        model.addAttribute("items", items.stream().map(ItemListViewResponse::new).collect(Collectors.toList()));
    }

    // 헤더에 마이페이지, 로그아웃 안보이게 하는 로직
    public void isSessionAvailable(Model model) {
        model.addAttribute("isSessionAvailable", "false");
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findByUserId(userId);
        // 작성한 판매글 List 삭제
        List<Item> items = itemDetailRepository.findAllByUser(user);
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

    public String editUserName(User user, String newUserName, HttpServletRequest httpServletRequest) {
        // 기존 아이디를 재 입력했을 경우
        if (user.getUserName().equals(newUserName)) {
            log.info("아이디 수정 실패 : 기존 아이디를 재입력함");
            return "redirect:/users/edit?notNew=error";
        }
        // 이미 존재하는 아이디일 경우
        User searchUser = userRepository.findByUserName(newUserName);
        if (searchUser != null) {
            log.info("아이디 수정 실패 : 이미 존재하는 계정");
            return "redirect:/users/edit?alreadyExists=error";
        }
        // 성공할 경우
        User target = userRepository.findByUserName(user.getUserName());
        target.setUserName(newUserName);
        userRepository.save(target);
        log.info("\n회원 아이디 변경 {} -> {} ", user.getUserName(), target.getUserName());
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("user", target);
        return "redirect:/users/edit?Success=Success";
    }

    public String editUserPassword(User user, String newPassword, String newPasswordCheck) {
        String oldPassword = Encrypt.md5(user.getPassword());
        String nuPassword = Encrypt.md5(newPassword);
        String nuPasswordCheck = Encrypt.md5(newPasswordCheck);

        // 비밀번호 불일치
        if (!nuPassword.equals(nuPasswordCheck)) {
            log.info("비밀번호 수정 실패 : 비밀번호가 일치하지 않음");
            return "redirect:/users/edit?PasswordNotCorrect=error";
        }
        // 기존 비밀번호 재 입력
        if (nuPassword.equals(oldPassword)) {
            log.info("비밀번호 수정 실패 : 기존 비밀번호와 동일함");
            return "redirect:/users/edit?PasswordNotNew=error";
        }
        // 성공
        User target = userRepository.findByUserName(user.getUserName());
        target.setPassword(newPassword);
        userRepository.save(target);
        log.info("비밀번호 수정 성공");
        return "redirect:/users/edit?PasswordUpdateSuccess=Success";


    }
}


