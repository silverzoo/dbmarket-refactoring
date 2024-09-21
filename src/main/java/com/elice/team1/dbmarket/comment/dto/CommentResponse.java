package com.elice.team1.dbmarket.comment.dto;

import com.elice.team1.dbmarket.user.entity.User;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Builder
public class CommentResponse {
    private Long id;
    private User user;
    private String reviewerName;
    private String content;
    private LocalDateTime createdAt;
    private long rating;


}