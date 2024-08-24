package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.Comment;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.service.CommentService;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//유저의 후기 조회
@RequiredArgsConstructor
@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @GetMapping("/{userid}")
    public String getAllCommentById(@PathVariable Long userid, Model model, HttpServletRequest httpServletRequest){
        // 로그인 세션 빌런

        User user = userService.getSessionUser(httpServletRequest);
        model.addAttribute("myusername", user.getUserName());
        model.addAttribute("myuserid", user.getUserId());

        List<Comment> comments = commentService.getAllCommentById(userid);
        model.addAttribute("comments",comments);
        return "comments";
    }
}
