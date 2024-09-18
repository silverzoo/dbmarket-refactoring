package com.elice.team1.prometheus.item.dto;

import com.elice.team1.prometheus.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemModifyRequest {
    private Long userId;
    private String name;
    private int price;
    private Long categoryId;
    private Category category;
    private String imagePath;
    private String description;
}
