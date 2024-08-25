package com.example.team1.Prometheus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@Getter
public class CommentResponse {
    private Long commentId;
    private Long userId;
    private String reviewerName;
    private String content;
    private Timestamp createdAt;

    public CommentResponse(Comment comment){
        this.commentId = comment.getCommentId();
        this.userId = comment.getUser().getUserId();
        this.reviewerName = comment.getReviewerName();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

}