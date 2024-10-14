package com.example.eventhub.exception.handler;

import com.example.eventhub.exception.ErrorResponse;
import com.example.eventhub.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

//@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return  ErrorResponse.builder()
                .errors(errors)
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getServletPath())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorResponse handleCardNotFoundException(UserNotFoundException exception,
                                                     HttpServletRequest request) {
        return  ErrorResponse.builder()
                .message(exception.getMessage())
                .timestamp(LocalDateTime.now())
                .path(request.getServletPath())
                .build();

    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception exception,
                                         HttpServletRequest request) {
        return  ErrorResponse.builder()
                .message("Something went wrong")
                .timestamp(LocalDateTime.now())
                .path(request.getServletPath())
                .build();

    }
}

