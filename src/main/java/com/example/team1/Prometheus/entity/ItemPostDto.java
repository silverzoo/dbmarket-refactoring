package com.example.team1.Prometheus.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ItemPostDto{
    private String name;
    private int price;
    private String Category;
    private Image image;
    private String description;

    public ItemPost toEntity() {
        ItemPost itemPost = new ItemPost(name,price,Category,image,description);
        return itemPost;
    }
}