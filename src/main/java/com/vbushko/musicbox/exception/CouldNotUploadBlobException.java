package com.vbushko.musicbox.exception;

public class CouldNotUploadBlobException extends RuntimeException {

    public CouldNotUploadBlobException(String message) {
        super(message);
    }

    public CouldNotUploadBlobException(String message, Throwable cause) {
        super(message, cause);
    }
}
