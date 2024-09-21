package com.elice.team1.dbmarket.comment.dto;

import com.elice.team1.dbmarket.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private Long id;
    private User user;
    private String reviewerName;
    private String content;
    private LocalDateTime createdAt;
    private long rating;
}