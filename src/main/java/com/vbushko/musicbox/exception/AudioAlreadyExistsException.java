package com.vbushko.musicbox.exception;

public class AudioAlreadyExistsException extends RuntimeException {

    public AudioAlreadyExistsException(String message) {
        super(message);
    }

    public AudioAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
