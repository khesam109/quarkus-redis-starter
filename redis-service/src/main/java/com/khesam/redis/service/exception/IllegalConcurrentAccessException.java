package com.khesam.redis.service.exception;

public class IllegalConcurrentAccessException extends DomainException {

    public IllegalConcurrentAccessException() {
    }

    public IllegalConcurrentAccessException(String message) {
        super(message);
    }

    public IllegalConcurrentAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
