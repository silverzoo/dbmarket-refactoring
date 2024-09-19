package com.elice.team1.dbmarket.category.mapper;

import com.elice.team1.dbmarket.category.entity.Category;
import com.elice.team1.dbmarket.category.dto.CategoryRequest;
import com.elice.team1.dbmarket.category.dto.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequest request);
    CategoryResponse toResponse(Category category);

}
