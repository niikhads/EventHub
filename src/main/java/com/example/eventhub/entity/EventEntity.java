package com.example.eventhub.entity;


import com.example.eventhub.enums.EventStatus;
import com.example.eventhub.enums.EventStatusConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
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

//    @JsonSerialize(using = EventStatusSerializer.class)
//    @JsonDeserialize(using = EventStatusDeserializer.class)
 //   @Enumerated(EnumType.ORDINAL)
    @Convert(converter = EventStatusConverter.class)
    EventStatus status;

    String location;

    LocalDateTime date;

    @Column(name = "price")
    Long price;

    @Column(name = "organizer_name")
    String organizerName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;


    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;


}
