package com.elice.team1.prometheus.item.dto;

import com.elice.team1.prometheus.category.entity.Category;
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
    private Category category;
    private String imagePath;
    private String description;
}
