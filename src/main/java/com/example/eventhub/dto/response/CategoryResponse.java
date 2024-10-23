package com.example.eventhub.dto.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    @Id
    Long id;
    String name;
//    LocalDateTime createdAt;
//    LocalDateTime updatedAt;
    }

