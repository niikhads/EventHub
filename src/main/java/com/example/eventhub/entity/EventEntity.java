package com.example.eventhub.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "events")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private Long id;

    String name;

    String description;

    String status;

    String location;

    LocalDateTime date;

    Long price;

    @Column(name = "organizer_name")
    String organizerName;

    @ManyToOne
    @JoinColumn(name = "id")
    Category category;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;



}
