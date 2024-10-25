package com.example.eventhub.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


public enum EventStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        ATTENDED;

}

