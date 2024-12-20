package com.example.eventhub.dto.request;

import com.example.eventhub.enums.EventStatus;
//import com.example.eventhub.enums.EventStatusDeserializer;
//import com.example.eventhub.enums.EventStatusSerializer;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventRequest {

     String name;

     String description;

     LocalDateTime date;

     String location;

     EventStatus status;

     Long price;

     String organizerName;

     Long categoryId;
}


