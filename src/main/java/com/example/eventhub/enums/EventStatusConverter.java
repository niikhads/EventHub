package com.example.eventhub.enums;



import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EventStatusConverter implements AttributeConverter<EventStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(EventStatus status) {
        if (status == null) {
            return null;
        }

        return status.ordinal(); // Enum dəyərini rəqəmə çevirir
    }

    @Override
    public EventStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return EventStatus.values()[dbData]; // Rəqəmi enum dəyərinə çevirir
    }
}

