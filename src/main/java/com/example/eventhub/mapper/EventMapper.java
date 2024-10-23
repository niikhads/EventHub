package com.example.eventhub.mapper;

import com.example.eventhub.dto.request.EventRequest;
import com.example.eventhub.dto.response.CategoryResponse;
import com.example.eventhub.dto.response.EventResponse;
import com.example.eventhub.entity.Category;
import com.example.eventhub.entity.EventEntity;
import org.mapstruct.*;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {

    @Mapping(source = "categoryId", target = "category", qualifiedByName = "mapCategoryIdToCategory")
    EventEntity toEntity(EventRequest eventRequest);

    @Mapping(source = "category", target = "category", qualifiedByName = "mapCategoryToCategoryResponse")
    EventResponse toResponse(EventEntity savedEntity);

    @Named("mapCategoryIdToCategory")
    default Category mapCategoryIdToCategory(Long categoryId) {
        if (categoryId == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }

    @Named("mapCategoryToCategoryResponse")
    default CategoryResponse mapCategoryToCategoryResponse(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}

