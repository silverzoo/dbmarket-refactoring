package com.elice.team1.dbmarket.item.dto;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.item.entity.Item;
import com.elice.team1.dbmarket.user.entity.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

//item DTO
@Getter
@Slf4j
public class ItemListViewResponse {
    private final String imagePath;
    private final Long id;
    private final User user;
    private final Category category;
    private final String name;
    private final int price;

    public ItemListViewResponse(Item item){
        this.imagePath = item.getImagePath();
        this.id = item.getId();
        this.user = item.getUser();
        this.category = item.getCategory();
        this.name = item.getName();
        this.price = item.getPrice();
    }


}
