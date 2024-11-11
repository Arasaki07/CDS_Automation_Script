package com.moodys.ma.ds.exceptions;

public class FileReadException extends RuntimeException {

    public FileReadException() {
        super();
    }

    public FileReadException(String message) {
        super(message);
    }

    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
