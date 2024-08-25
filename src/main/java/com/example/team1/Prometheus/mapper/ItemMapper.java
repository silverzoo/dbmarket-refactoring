package com.example.team1.Prometheus.mapper;

import com.example.team1.Prometheus.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    Item toEntity(ItemModifyRequest request);
    ItemModifyResponse toResponse(Item item);

    ItemDeleteResponse toDeleteResponse(Item item);

    ItemResponse toItemResponse(Item item);

}