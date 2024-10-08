package com.elice.team1.dbmarket.user.service;

import com.elice.team1.dbmarket.comment.entity.Comment;
import com.elice.team1.dbmarket.common.exception.NotFoundUserbyUserId;
import com.elice.team1.dbmarket.common.util.Encrypt;
import com.elice.team1.dbmarket.item.entity.Item;
import com.elice.team1.dbmarket.item.mapper.ItemMapper;
import com.elice.team1.dbmarket.user.dto.UserDto;
import com.elice.team1.dbmarket.user.entity.User;
import com.elice.team1.dbmarket.comment.repository.CommentRepository;
import com.elice.team1.dbmarket.item.repository.ItemRepository;
import com.elice.team1.dbmarket.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final CommentRepository commentRepository;
    private final ItemMapper itemMapper;

    public UserService(UserRepository userRepository, ItemRepository itemRepository,
                       CommentRepository commentRepository, ItemMapper itemMapper) {
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.commentRepository = commentRepository;
        this.itemMapper = itemMapper;
    }

    public String createUser(UserDto form, String password_check, HttpServletRequest httpServletRequest) {
        User user = form.toEntity();
        User name = userRepository.findByUsername(form.getUsername());

        if (user.getUsername().contains(" ") || user.getPassword().contains(" ") || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
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
        User user = userRepository.findByUsernameAndPassword(username, password);

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
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserbyUserId(userId));
        return user;
    }


    public String findUsername(Long userId) {
        User user = findUserById(userId);
        return user.getUsername();
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    public User getSessionUser(HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");
        return user;
    }


    public void getItemsByUserId(Long userId, Model model) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserbyUserId(userId));
        List<Item> items = itemRepository.findAllByUser(user);
        model.addAttribute("items", items.stream().map(itemMapper::toItemResponse).collect(Collectors.toList()));
    }

    // 헤더에 마이페이지, 로그아웃 안보이게 하는 로직
    public void isSessionAvailable(Model model) {
        model.addAttribute("isSessionAvailable", "false");
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserbyUserId(userId));
        // 작성한 판매글 List 삭제
        List<Item> items = itemRepository.findAllByUser(user);
        for (Item item : items) {
            log.info("\n회원이 작성한 게시글 : {} 삭제 \n", item.getName());
            itemRepository.delete(item);
        }

        // 탈퇴한 회원이 작성한 코멘트 삭제
        List<Comment> comments = commentRepository.findAllByUser(user);
        for (Comment comment : comments) {
            log.info("\n회원이 작성한 코멘트 : {} 삭제 \n", comment.getContent());
            commentRepository.delete(comment);
        }

        // 회원에게 작성된 코멘트 삭제
        List<Comment> comments1 = commentRepository.findAllByReviewerName(user.getUsername());
        for (Comment comment : comments1) {
            log.info("\n회원에게 작성된 코멘트 : {} 삭제 \n", comment.getContent());
            commentRepository.delete(comment);
        }

        userRepository.delete(user);
        log.info("\n회원 삭제 : {} 삭제 \n", user.getUsername());
    }

    public String editUserName(User user, String newUsername, HttpServletRequest httpServletRequest) {
        // 기존 아이디를 재 입력했을 경우
        if (user.getUsername().equals(newUsername)) {
            log.info("아이디 수정 실패 : 기존 아이디를 재입력함");
            return "redirect:/users/edit?notNew=error";
        }
        // 이미 존재하는 아이디일 경우
        User searchUser = userRepository.findByUsername(newUsername);
        if (searchUser != null) {
            log.info("아이디 수정 실패 : 이미 존재하는 계정");
            return "redirect:/users/edit?alreadyExists=error";
        }
        // 성공할 경우
        User target = userRepository.findByUsername(user.getUsername());
        target.setUsername(newUsername);
        userRepository.save(target);
        log.info("\n회원 아이디 변경 {} -> {} ", user.getUsername(), target.getUsername());
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
        User target = userRepository.findByUsername(user.getUsername());
        target.setPassword(newPassword);
        userRepository.save(target);
        log.info("비밀번호 수정 성공");
        return "redirect:/users/edit?PasswordUpdateSuccess=Success";


    }
}


