package com.example.eventhub.dto.response;

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
         String status;
         String location;
         Long price;
         String organizerName;
         CategoryResponse category;

//         @CreationTimestamp
//         LocalDateTime createdAt;
//         @UpdateTimestamp
//         LocalDateTime updatedAt;
    }


