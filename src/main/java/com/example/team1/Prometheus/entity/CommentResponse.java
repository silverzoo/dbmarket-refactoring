package com.example.team1.Prometheus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CommentResponse {
    private Long commentId;
    private Long userId;
    private String reviewerName;
    private String content;
    private LocalDateTime createdAt;
    private long rating;

    public CommentResponse(Comment comment){
        this.commentId = comment.getCommentId();
        this.userId = comment.getUser().getUserId();
        this.reviewerName = comment.getReviewerName();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.rating = comment.getRating();
    }

}