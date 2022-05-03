package com.vbushko.musicbox.exception;

public class EntityDoesNotExistException extends RuntimeException {

    public EntityDoesNotExistException(String message) {
        super(message);
    }

    public EntityDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
