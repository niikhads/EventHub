package com.example.eventhub.enums;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class EventStatusSerializer extends JsonSerializer<EventStatus> {
    @Override
    public void serialize(EventStatus value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Enum-un ordinal dəyərini JSON-a yazır
        gen.writeNumber(value.ordinal());
    }
}

