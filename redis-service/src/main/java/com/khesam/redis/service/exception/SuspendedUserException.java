package com.khesam.redis.service.exception;

public class SuspendedUserException extends SessionManagementException {

    private static final String MESSAGE = "User: %s is suspended!";

    public SuspendedUserException(String username) {
        super(String.format(MESSAGE, username));
    }

    public SuspendedUserException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
