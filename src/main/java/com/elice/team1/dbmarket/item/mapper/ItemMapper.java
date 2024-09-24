package com.elice.team1.dbmarket.item.mapper;

import com.elice.team1.dbmarket.item.dto.ItemDeleteResponse;
import com.elice.team1.dbmarket.item.dto.ItemModifyRequest;
import com.elice.team1.dbmarket.item.dto.ItemModifyResponse;
import com.elice.team1.dbmarket.item.dto.ItemResponse;
import com.elice.team1.dbmarket.item.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemModifyRequest request);
    ItemModifyResponse toModifyResponse(Item item);

    ItemDeleteResponse toDeleteResponse(Item item);

    default ItemResponse toItemResponse(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .userId(item.getUser().getId())
                .categoryId(item.getCategory().getId())
                .name(item.getName())
                .price(item.getPrice())
                .imagePath(item.getImagePath())
                .description(item.getDescription())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }
}