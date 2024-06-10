package com.pragma.food_cout.configuration.exceptionhandler;

import com.pragma.food_cout.domain.exception.BadRequestValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class ControlAdvisor {
    @ExceptionHandler(BadRequestValidationException.class)
    public ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestValidationException ex) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ex.getMessage(),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(new ExceptionResponse(errors,
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }
}
