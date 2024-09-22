package com.elice.team1.dbmarket.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest {
    private Long id;
    private Long userId;
    private String reviewerName;
    private String content;
    private long rating;
}