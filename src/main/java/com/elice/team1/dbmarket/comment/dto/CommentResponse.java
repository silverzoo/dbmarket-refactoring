package com.elice.team1.dbmarket.comment.dto;

import lombok.*;
import java.time.LocalDateTime;


@Getter
@Builder
public class CommentResponse {
    private Long id;
    private Long userId;
    private String reviewerName;
    private String content;
    private long rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}