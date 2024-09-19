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

    ItemResponse toItemResponse(Item item);

}