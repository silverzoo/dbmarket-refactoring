package com.elice.team1.prometheus.comment.dto;

import com.elice.team1.prometheus.user.entity.User;
import lombok.*;
import java.time.LocalDateTime;


@Getter
@Builder
public class CommentResponse {
    private Long commentId;
    private User user;
    private String reviewerName;
    private String content;
    private LocalDateTime createdAt;
    private long rating;


}