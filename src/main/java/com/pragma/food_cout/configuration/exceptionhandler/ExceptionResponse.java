package com.pragma.food_cout.configuration.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private final String message;
    private final String status;
    private final LocalDateTime timestamp;
    private final Object errors;

    public ExceptionResponse(String message, String status, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
        this.errors = null;
    }

    public ExceptionResponse(Object errors, String status, LocalDateTime timestamp) {
        this.message = null;
        this.errors = errors;
        this.status = status;
        this.timestamp = timestamp;
    }
}
