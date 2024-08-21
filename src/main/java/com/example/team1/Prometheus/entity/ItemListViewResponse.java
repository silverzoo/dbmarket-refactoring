package com.example.team1.Prometheus.entity;

import lombok.Getter;

//item DTO
@Getter
public class ItemListViewResponse {
    private final String name;
    private final int price;

    public ItemListViewResponse(ItemDetail itemDetail){
        this.name = itemDetail.getName();
        this.price = itemDetail.getPrice();
    }


}
