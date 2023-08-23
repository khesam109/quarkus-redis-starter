package com.khesam.redis.service.exception;

public class InvalidCredentials extends SessionManagementException {

    private static final String MESSAGE = "Invalid credentials";

    public InvalidCredentials() {
        super(MESSAGE);
    }

    public InvalidCredentials(Throwable cause) {
        super(MESSAGE, cause);
    }
}
