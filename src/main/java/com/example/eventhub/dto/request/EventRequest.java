package com.example.eventhub.dto.request;

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
     LocalDateTime time;
     String location;
     Long price;
     String organizerName;

     Long categoryId;

}

