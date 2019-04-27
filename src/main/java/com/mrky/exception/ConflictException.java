package com.mrky.exception;

public class ConflictException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConflictException() {
    }

    public ConflictException(String message) {
        super(message);
    }
}