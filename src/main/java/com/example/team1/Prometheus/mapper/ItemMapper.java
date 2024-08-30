package com.example.team1.Prometheus.mapper;

import com.example.team1.Prometheus.entity.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemModifyRequest request);
    ItemModifyResponse toModifyResponse(Item item);

    ItemDeleteResponse toDeleteResponse(Item item);

    ItemResponse toItemResponse(Item item);

}