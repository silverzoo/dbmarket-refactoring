package com.elice.team1.prometheus.item.mapper;

import com.elice.team1.prometheus.item.dto.ItemDeleteResponse;
import com.elice.team1.prometheus.item.dto.ItemModifyRequest;
import com.elice.team1.prometheus.item.dto.ItemModifyResponse;
import com.elice.team1.prometheus.item.dto.ItemResponse;
import com.elice.team1.prometheus.item.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(ItemModifyRequest request);
    ItemModifyResponse toModifyResponse(Item item);

    ItemDeleteResponse toDeleteResponse(Item item);

    ItemResponse toItemResponse(Item item);

}