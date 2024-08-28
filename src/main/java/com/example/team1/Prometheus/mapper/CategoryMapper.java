package com.example.team1.Prometheus.mapper;

import com.example.team1.Prometheus.entity.Category;
import com.example.team1.Prometheus.entity.CategoryRequest;
import com.example.team1.Prometheus.entity.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequest request);
    CategoryResponse toResponse(Category category);

}
