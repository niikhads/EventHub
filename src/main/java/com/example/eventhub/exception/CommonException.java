package com.example.eventhub.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommonException extends RuntimeException{
    private String code;
    private String description;
    private HttpStatusCode httpStatusCode;
}