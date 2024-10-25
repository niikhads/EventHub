package com.example.eventhub.enums;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class EventStatusDeserializer extends JsonDeserializer<EventStatus> {
    @Override
    public EventStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        // JSON-dan rəqəm alıb, onu Enum ordinal dəyərinə çevririk
        int ordinal = p.getIntValue();
        return EventStatus.values()[ordinal];  // Rəqəmi Enum-a çeviririk
    }
}

