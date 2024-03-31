package com.oles.airmanagement.exception;

public class AlreadyExistException extends IllegalStateException {
    private static final String ALREADY_EXIST_EXCEPTION = "Already exist";

    public AlreadyExistException(String message) {
        super(message.isEmpty() ? ALREADY_EXIST_EXCEPTION : message);
    }
}
