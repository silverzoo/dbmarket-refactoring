package com.elice.team1.prometheus.comment.dto;

import lombok.*;
import java.time.LocalDateTime;


@Getter
@Builder
public class CommentResponse {
    private Long commentId;
    private Long userId;
    private String reviewerName;
    private String content;
    private LocalDateTime createdAt;
    private long rating;


}