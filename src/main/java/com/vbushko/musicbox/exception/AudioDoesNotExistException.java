package com.vbushko.musicbox.exception;

public class AudioDoesNotExistException extends RuntimeException {

    public AudioDoesNotExistException(String message) {
        super(message);
    }

    public AudioDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
