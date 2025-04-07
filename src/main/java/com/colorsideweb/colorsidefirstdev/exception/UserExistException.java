package com.colorsideweb.colorsidefirstdev.exception;

public class UserExistException extends RuntimeException {
    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }
}
