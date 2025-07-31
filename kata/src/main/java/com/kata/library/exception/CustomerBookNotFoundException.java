package com.kata.library.exception;

public class CustomerBookNotFoundException extends RuntimeException {
    public CustomerBookNotFoundException(String message) {
        super(message);
    }
}