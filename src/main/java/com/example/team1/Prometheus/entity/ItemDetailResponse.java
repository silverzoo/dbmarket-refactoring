package com.example.team1.Prometheus.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class ItemDetailResponse {
    private Long itemId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // TODO : 엔티티 <-> DTO mapstruct 사용해서 리팩토링
    public ItemDetailResponse(ItemDetailRequest itemDetail) {
        this.itemId = itemDetail.getItemId();
        this.name = itemDetail.getName();
        this.description = itemDetail.getDescription();
        this.createdAt = itemDetail.getCreatedAt();
        this.updatedAt = itemDetail.getUpdatedAt();
    }

    public ItemDetailResponse(ItemDetail itemDetail) {
        this.itemId = itemDetail.getItemId();
        this.name = itemDetail.getName();
        this.description = itemDetail.getDescription();
        this.createdAt = itemDetail.getCreatedAt();
        this.updatedAt = itemDetail.getUpdatedAt();
    }

}
