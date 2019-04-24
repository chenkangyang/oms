package com.mrky.exception;

public class DuplicationException extends Exception {
    public DuplicationException() {
        super();
    }

    public DuplicationException(Throwable cause) {
        super(cause);
    }

    public DuplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}