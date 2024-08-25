package com.example.team1.Prometheus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class ItemDetailResponse {
    private Long itemId;
    private Long userId;
    private String name;
    private int price;
    private String category;
    private String imagePath;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // TODO : 엔티티 <-> DTO mapstruct 사용해서 리팩토링
//    public ItemDetailResponse(ItemDetailRequest item) {
//        this.itemId = item.getItemId();
//        this.name = item.getName();
//        this.description = item.getDescription();
//        this.createdAt = item.getCreatedAt();
//        this.updatedAt = item.getUpdatedAt();
//    }

    public ItemDetailResponse(Item item) {
        this.itemId = item.getItemId();
        this.userId = item.getUserId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.category = item.getCategory();
        this.imagePath = item.getImagePath();
        this.description = item.getDescription();
        this.createdAt = item.getCreatedAt();
        this.updatedAt = item.getUpdatedAt();
    }

}
