package com.autentia.academioboot.exception;

public class StorageException extends RuntimeException {

    private static final long serialVersionUID = -5999737410968633971L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}