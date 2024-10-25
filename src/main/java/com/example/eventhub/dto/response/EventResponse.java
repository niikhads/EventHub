package com.example.eventhub.dto.response;

import com.example.eventhub.enums.EventStatus;
//import com.example.eventhub.enums.EventStatusSerializer;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventResponse {
         Long id;

         String name;

         String description;

         LocalDateTime date;

         EventStatus status;

         String location;

         Long price;

         String organizerName;

         CategoryResponse category;

    }


