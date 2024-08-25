package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.*;
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
    @Autowired
    private HttpServletRequest httpServletRequest;

    // 판매자의 모든 댓글 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentById(@PathVariable("userId") long userId) {
        List<CommentResponse> commentResponses = commentService.getAllCommentById(userId);
        return ResponseEntity.ok().body(commentResponses);
    }


    // 댓글 상세 페이지의 데이터 조회
    @GetMapping("/detail/{commentId}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable("commentId") long commentId) {
        CommentResponse commentResponse = commentService.getCommentById(commentId);

        return ResponseEntity.ok().body(commentResponse);
    }


    // 해당 commentId의 댓글 수정
    @PutMapping("/detail/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("commentId") long commentId, @RequestBody CommentRequest commentRequest) {

        CommentResponse response = commentService.updateComment(commentId, commentRequest);
        return ResponseEntity.ok().body(response);
    }


    // 해당 commentId 댓글 삭제
    @DeleteMapping("/detail/{commentId}")
    public ResponseEntity<CommentDeleteResponse> deleteComment(@PathVariable("commentId") long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }



}
