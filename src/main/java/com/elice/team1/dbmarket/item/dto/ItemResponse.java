package com.elice.team1.dbmarket.item.dto;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class ItemResponse {
    private Long id;
    private User user;
    private String name;
    private int price;
    private Category category;
    private String imagePath;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
