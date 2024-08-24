package com.example.team1.Prometheus.controller;

import com.example.team1.Prometheus.entity.CommentDeleteResponse;
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
    @Autowired
    private HttpServletRequest httpServletRequest;

    //판매자의 모든 댓글 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<CommentResponse>> getAllCommentById(@PathVariable("userId") Long userId) {
        List<CommentResponse> commentResponses = commentService.getAllCommentById(userId);
        return ResponseEntity.ok().body(commentResponses);
    }


    //해당 commentId의 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("commentId") Long commentId, @RequestBody CommentRequest commentRequest) {

        CommentResponse response = commentService.updateComment(commentId, commentRequest);
        return ResponseEntity.ok().body(response);
    }

    /*
    //해당 commentId 댓글 삭제
    @DeleteMapping("/{Id}")
    public ResponseEntity<CommentDeleteResponse> deleteComment(Long commentId) {
        commentService.deleteComment(comment.userId);
        return ResponseEntity.ok().build();
    }
*/


}
