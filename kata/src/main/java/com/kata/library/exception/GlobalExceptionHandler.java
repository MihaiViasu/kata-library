package com.kata.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<?> handleBookNotFound(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error(ex));
    }

    @ExceptionHandler(CustomerBookNotFoundException.class)
    public ResponseEntity<?> handleCustomerBookNotFound(CustomerBookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error(ex));
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFound(CustomerNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error(ex));
    }

    @ExceptionHandler(NoAvailableCopiesException.class)
    public ResponseEntity<?> handleNoCopies(NoAvailableCopiesException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error(ex));
    }

    private Map<String, String> error(Exception ex) {
        return Map.of("error", ex.getMessage());
    }
}