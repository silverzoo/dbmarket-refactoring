package com.elice.team1.prometheus.comment.dto;

import com.elice.team1.prometheus.user.entity.User;
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
    private User user;
    private String reviewerName;
    private String content;
    private LocalDateTime createdAt;
    private long rating;
}