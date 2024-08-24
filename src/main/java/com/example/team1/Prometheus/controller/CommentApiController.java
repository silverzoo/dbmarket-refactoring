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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApiController {
    private final CommentService commentService;

    @Autowired
    private UserService userService;

    private HttpServletRequest httpServletRequest;
    /*
        //판매자의 모든 댓글 조회
        @GetMapping("{userId}")
        public ResponseEntity<CommentResponse> findComment(@PathVariable Long userId) {
            CommentResponse commentResponse = CommentService.getAllCommentById(userId);
            return ResponseEntity.ok().body(commentResponse);}
        */
    User user = userService.getSessionUser(httpServletRequest);
    String userName = user.getUserName();

    //해당 commentId의 댓글 수정
    //
    @PutMapping("/{reviewerName}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId, @RequestBody CommentRequest commentRequest) {
        CommentResponse response = commentService.updateComment(commentId, commentRequest,userName);
        return ResponseEntity.ok().body(response);
    }

    //해당 commentId의 댓글 삭제
    @DeleteMapping("/{reviewerName}")
    public ResponseEntity<CommentResponse> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }



}
