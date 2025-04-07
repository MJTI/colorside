package com.colorsideweb.colorsidefirstdev.exception;

public class PostExistException extends RuntimeException {
    public PostExistException() {
    }

    public PostExistException(String message) {
        super(message);
    }
}
