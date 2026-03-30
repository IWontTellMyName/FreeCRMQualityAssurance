package com.freecrm.automation.exceptions;

public class DeserializationException extends RuntimeException {

    public DeserializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeserializationException(String message) {
        super(message);
    }

}
