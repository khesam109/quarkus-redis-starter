package com.khesam.redis.service.exception;

public class SessionManagementException extends DomainException {

    public SessionManagementException() {
    }

    public SessionManagementException(String message) {
        super(message);
    }

    public SessionManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
