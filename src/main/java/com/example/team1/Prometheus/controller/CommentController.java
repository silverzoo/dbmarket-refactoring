package com.example.team1.Prometheus.controller;
import com.example.team1.Prometheus.entity.CommentRequest;
import com.example.team1.Prometheus.entity.CommentResponse;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.service.CommentService;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//유저의 후기 조회
@Controller//comments엿음
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    private UserService userService;
    private HttpServletRequest request;

    @Autowired
    public CommentController(CommentService commentService,HttpServletRequest request, UserService userService) {

        this.commentService = commentService;
        this.request = request;
        this.userService = userService;
    }

    //한 사람의 댓글 모두 조회
    @GetMapping("/{userId}")
    public String getAllCommentById(@PathVariable Long userId, Model model) {
        // CommentResponse 리스트를 반환하도록 수정
        List<CommentResponse> comments = commentService.getAllCommentById(userId);

        // 모델에 CommentResponse 리스트 추가
        model.addAttribute("comments", comments);

        // 해당 파일을 찾아 렌더링
        return "comment/comments";
    }


    //댓글 수정 페이지 이동
    @GetMapping("edit/{commentId}")
    public String updateComment(@PathVariable("commentId") Long commentId, Model model){
        CommentResponse comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        return "comment/edit";
    }

    @PostMapping("/{commentId}")
    public String updateComment(@PathVariable("commentId") Long commentId,
                                @ModelAttribute CommentRequest commentRequest,
                                HttpServletRequest httpServletRequest) {
        // 현재 세션 사용자 정보를 가져오는 서비스 호출
        User user = userService.getSessionUser(httpServletRequest);
        String userName = user.getUserName();

        // 댓글 수정 서비스 호출
        commentService.updateComment(commentId, commentRequest, userName);

        // 수정 후 댓글 목록 페이지로 리디렉션
        return "redirect:/comments" ;
    }




}
