package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.*;
import com.example.team1.Prometheus.service.CommentService;
import com.example.team1.Prometheus.service.UserFilter;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 유저의 후기 조회
@RequiredArgsConstructor
@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserFilter userFilter;
    private final UserService userService;

    // 모든 댓글 조회
    @GetMapping("/{userId}")
    public String getAllCommentById(@PathVariable long userId, Model model){
        userFilter.findUserByFilter(model);
        List<CommentResponse> comments = commentService.getAllCommentById(userId);
        model.addAttribute("comments",comments);
        return "comment/comments";
    }

    // 댓글 작성
    @PostMapping("/create")
    public String createComment(@ModelAttribute CommentRequest commentRequest,
                                @RequestParam("reviewerName") String reviewerName) {
        commentService.createComment(commentRequest, reviewerName);
        return "redirect:/comments/" + commentRequest.getUserId();
    }


    // 상세 페이지로 이동
    @GetMapping("/detail/{commentId}")
    public String getComment(@PathVariable Long commentId, Model model) {

        // 1. 세션 정보 가져오기
        String user1 = userFilter.findUserByFilter(model).getUserName();
        System.out.println(user1);

        // 2. 댓글 작성자 가져오기
        CommentResponse comment = commentService.getCommentById(commentId);
        String user2 = comment.getReviewerName();
        System.out.println(user2);

        // 3. 모델에 등록하기
        model.addAttribute("user1",user1);
        model.addAttribute("user2",user2);


        model.addAttribute("comment", comment);
        return "comment/detail";
    }

    // 댓글 수정 페이지 이동
    @GetMapping("edit/{commentId}")
    public String updateComment(@PathVariable("commentId") Long commentId, Model model){
        CommentResponse comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "comment/edit";
    }

    // 수정 후 상세 페이지로 리다이렉트
    @PostMapping("/detail/{commentId}")
    public String updateComment(@PathVariable("commentId") Long commentId,
                                @ModelAttribute CommentRequest commentRequest) {

        // 댓글 수정 서비스 호출
        commentService.updateComment(commentId, commentRequest);
        // 수정 후 댓글 목록 페이지로 리디렉션
        return "redirect:/comments/" + commentRequest.getUserId();
    }
    



}
