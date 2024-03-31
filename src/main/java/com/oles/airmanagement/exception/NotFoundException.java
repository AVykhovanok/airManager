package com.oles.airmanagement.exception;

public class NotFoundException extends IllegalArgumentException {
    private static final String NOT_EXIST_EXCEPTION = "Not exist";

    public NotFoundException(String message) {
        super(message.isEmpty() ? NOT_EXIST_EXCEPTION : message);
    }
}
