package com.elice.team1.dbmarket.item.dto;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemModifyRequest {
    private User user;
    private String name;
    private int price;
    private Category category;
    private String imagePath;
    private String description;
}
