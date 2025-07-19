package com.example.demo.Exceptions;

public class PanelLoadException extends Exception {
    public PanelLoadException(String message) {
        super(message);
    }

    public PanelLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
