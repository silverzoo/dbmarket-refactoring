package com.example.team1.Prometheus.entity;

import lombok.Getter;

//item DTO
@Getter
public class ItemListViewResponse {
    private final Long itemId;
    private final String name;
    private final int price;

    public ItemListViewResponse(Item item){
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.price = item.getPrice();
    }


}
