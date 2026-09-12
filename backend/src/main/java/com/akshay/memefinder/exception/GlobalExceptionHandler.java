package com.akshay.memefinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// Handles exceptions thrown from REST controllers globally.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles failures that occur while searching an external meme source.
    @ExceptionHandler(MemeSearchException.class)
    public ResponseEntity<Map<String, String>> handleMemeSearchException(
            MemeSearchException e) {

        // Return HTTP 502 because our server is working,
        // but communication with the external meme source failed.
        return ResponseEntity
                .status(HttpStatus.BAD_GATEWAY)
                .body(Map.of("message", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
            IllegalArgumentException e) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("message", e.getMessage()));
    }
}