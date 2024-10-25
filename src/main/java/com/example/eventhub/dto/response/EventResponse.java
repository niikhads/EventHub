package com.example.eventhub.dto.response;

import com.example.eventhub.enums.EventStatus;
//import com.example.eventhub.enums.EventStatusSerializer;
import com.example.eventhub.enums.EventStatusDeserializer;
import com.example.eventhub.enums.EventStatusSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

//@JsonSerialize(using = EventStatusSerializer.class)
//@JsonDeserialize(using = EventStatusDeserializer.class)
         EventStatus status;

         String location;
         Long price;
         String organizerName;
         CategoryResponse category;

//         @CreationTimestamp
//         LocalDateTime createdAt;
//         @UpdateTimestamp
//         LocalDateTime updatedAt;
    }


