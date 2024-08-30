package com.example.team1.Prometheus.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private Long commentId;
    private Long userId;
    private String reviewerName;
    private String content;
    private LocalDateTime createdAt;
    private long rating;
}