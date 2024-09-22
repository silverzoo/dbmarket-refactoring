package com.elice.team1.dbmarket.item.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ItemResponse {
    private Long id;
    private Long userId;
    private Long categoryId;
    private String name;
    private int price;
    private String imagePath;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
