package com.example.team1.Prometheus.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class ItemModifyResponse {
    private Long itemId;
    private Long userId;
    private String name;
    private int price;
    private Long categoryId;
    private String category;
    private String imagePath;
    private String description;
}
