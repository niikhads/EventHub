package com.example.eventhub.mapper;

import com.example.eventhub.dto.request.CategoryRequest;
import com.example.eventhub.dto.response.CategoryResponse;
import com.example.eventhub.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryRequest categoryRequest);

    CategoryResponse toResponse(Category category);
}
