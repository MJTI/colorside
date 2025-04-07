package com.colorsideweb.colorsidefirstdev.exception;

public class InvalidPostException extends RuntimeException {
    public InvalidPostException() {
    }

    public InvalidPostException(String message) {
        super(message);
    }
}
