package com.example.team1.Prometheus.entity;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//item DTO
@Getter
@Slf4j
public class ItemListViewResponse {
    private final String imagePath;
    private final Long itemId;
    private final Long userId;
    private Long categoryId;
    private final String category;
    private final String name;
    private final int price;

    public ItemListViewResponse(Item item){
        this.imagePath = item.getImagePath();
        this.itemId = item.getItemId();
        this.userId = item.getUserId();
        this.categoryId = item.getCategoryId();
        this.category = item.getCategory();
        this.name = item.getName();
        this.price = item.getPrice();
    }


}
