package com.example.eventhub.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//@JsonSerialize(using = EventStatusSerializer.class)
//@JsonDeserialize(using = EventStatusDeserializer.class)
public enum EventStatus {
        PENDING(0),
        CONFIRMED(1),
        CANCELLED(2),
        ATTENDED(3);

        private final int value;

        EventStatus(int value) {
                this.value = value;
        }

        @JsonValue
        public int getValue() {
                return value;
        }

        @JsonCreator
        public static EventStatus forValue(int value) {
                for (EventStatus status : EventStatus.values()) {
                        if (status.value == value) {
                                return status;
                        }
                }
                throw new IllegalArgumentException("Unknown value: " + value);
        }
}

