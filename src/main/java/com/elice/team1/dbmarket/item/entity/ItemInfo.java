package com.elice.team1.dbmarket.item.entity;

import com.elice.team1.dbmarket.category.entity.Category;
import lombok.Getter;
import lombok.Setter;

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
