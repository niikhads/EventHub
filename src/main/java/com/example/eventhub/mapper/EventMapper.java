package com.example.eventhub.mapper;

import com.example.eventhub.dto.request.EventRequest;
import com.example.eventhub.dto.response.EventResponse;
import com.example.eventhub.entity.EventEntity;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",imports={LocalDateTime.class})
public interface EventMapper {


    EventEntity toEntity(EventRequest eventRequest);

    EventResponse toResponse(EventEntity eventEntity);
}
