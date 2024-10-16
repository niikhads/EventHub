package com.example.eventhub.mapper;

import com.example.eventhub.dto.request.CategoryRequest;
import com.example.eventhub.dto.response.CategoryResponse;
import com.example.eventhub.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;


@Mapper(componentModel = "spring",imports={LocalDateTime.class})
public interface CategoryMapper {

    Category toEntity(CategoryRequest categoryRequest);

    CategoryResponse toResponse(Category category);
}
