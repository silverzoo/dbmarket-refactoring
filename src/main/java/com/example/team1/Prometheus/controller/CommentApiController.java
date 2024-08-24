package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.CommentRequest;
import com.example.team1.Prometheus.entity.CommentResponse;
import com.example.team1.Prometheus.entity.User;
import com.example.team1.Prometheus.service.CommentService;
import com.example.team1.Prometheus.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApiController {
    private final CommentService commentService;
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    //판매자의 모든 댓글 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentById(@PathVariable("userId") Long userId) {
        List<CommentResponse> commentResponses = commentService.getAllCommentById(userId);
        return ResponseEntity.ok().body(commentResponses);
    }


    //해당 commentId의 댓글 수정
    //세션에서 유저 네임= 댓글 객체 리뷰어 네임과 일치하면 해당 댓글을 삭제할 수 있게
    //모든 댓글에 수정/삭제 버튼은 다 댓글 떠야겠는데..
    //근데 저 두번째 규칙이 일치해야 수정페이지 -> 삭제 페이지로
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentRequest commentRequest) {

        User user = userService.getSessionUser(httpServletRequest);
        String userName = user.getUserName();

        CommentResponse response = commentService.updateComment(commentId, commentRequest, userName);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentId") Long commentId) {
        User user = userService.getSessionUser(httpServletRequest);
        String userName = user.getUserName();

        commentService.deleteComment(commentId, userName);
        return ResponseEntity.ok().build();
    }



}
