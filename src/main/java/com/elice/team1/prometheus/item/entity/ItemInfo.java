package com.elice.team1.prometheus.item.entity;

import com.elice.team1.prometheus.category.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemInfo {
    private Long userId;
    private String name;
    private int price;
    private Long categoryId;
//    private String category;
    private Category category;
    private String imagePath;
    private String description;
}
