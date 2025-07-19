package com.example.demo.Exceptions;

public class SoundManagerException extends Exception {
    public SoundManagerException(String message) {
        super(message);
    }

    public SoundManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
