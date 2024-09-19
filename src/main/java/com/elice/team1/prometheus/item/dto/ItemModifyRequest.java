package com.elice.team1.prometheus.item.dto;

import com.elice.team1.prometheus.category.entity.Category;
import com.elice.team1.prometheus.user.entity.User;
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
