package com.example.demo.Exceptions;

public class SceneSwitchException extends Exception {
    public SceneSwitchException(String message) {
        super(message);
    }

    public SceneSwitchException(String message, Throwable cause) {
        super(message, cause);
    }
}