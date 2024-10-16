package com.example.eventhub.mapper;

import com.example.eventhub.dto.request.EventRequest;
import com.example.eventhub.dto.response.CategoryResponse;
import com.example.eventhub.dto.response.EventResponse;
import com.example.eventhub.entity.Category;
import com.example.eventhub.entity.EventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public interface EventMapper {

    EventEntity toEntity(EventRequest eventRequest);

    // Burada category sahəsini map etməliyik
    @Mapping(target = "category", source = "category")
    EventResponse toResponse(EventEntity eventEntity);

    // `Category`-dən `CategoryResponse`-ə çevirmək üçün əlavə bir metodu nəzərdə tuta bilərsiniz
    CategoryResponse toCategoryResponse(Category category);
}
