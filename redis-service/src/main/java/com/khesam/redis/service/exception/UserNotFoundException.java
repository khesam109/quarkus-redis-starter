package com.khesam.redis.service.exception;

public class UserNotFoundException extends SessionManagementException {

    private static final String MESSAGE = "Invalid user id";

    public UserNotFoundException() {
        super(MESSAGE);
    }

    public UserNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
