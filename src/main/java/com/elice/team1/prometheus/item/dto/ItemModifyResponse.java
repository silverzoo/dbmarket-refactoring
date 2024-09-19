package com.elice.team1.prometheus.item.dto;

import com.elice.team1.prometheus.category.entity.Category;
import com.elice.team1.prometheus.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class ItemModifyResponse {
    private Long id;
    private User user;
    private String name;
    private int price;
    private Category category;
    private String imagePath;
    private String description;
}
