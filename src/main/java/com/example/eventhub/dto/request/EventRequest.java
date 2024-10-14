package com.example.eventhub.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

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
     Long price;
     String organizerName;
     Long categoryId;

}

