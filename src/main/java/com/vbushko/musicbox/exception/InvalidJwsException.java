package com.vbushko.musicbox.exception;

public class InvalidJwsException extends RuntimeException {

    public InvalidJwsException(String message) {
        super(message);
    }

    public InvalidJwsException(String message, Throwable cause) {
        super(message, cause);
    }
}
