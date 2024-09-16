package com.elice.team1.prometheus.comment.controller;

import com.elice.team1.prometheus.comment.dto.CommentDeleteResponse;
import com.elice.team1.prometheus.comment.dto.CommentRequest;
import com.elice.team1.prometheus.comment.dto.CommentResponse;
import com.elice.team1.prometheus.category.service.CommentService;
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


    // 해당 commentId의 댓글 수정
    @PutMapping("/edit/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("commentId") long commentId, @RequestBody CommentRequest commentRequest) {

        CommentResponse response = commentService.updateComment(commentId, commentRequest);
        return ResponseEntity.ok().body(response);
    }


    // 해당 commentId 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<CommentDeleteResponse> deleteComment(@PathVariable("commentId") long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }



}
