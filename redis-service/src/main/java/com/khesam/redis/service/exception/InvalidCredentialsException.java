package com.khesam.redis.service.exception;

public class InvalidCredentialsException extends SessionManagementException {

    private static final String MESSAGE = "Invalid credentials. You have (%s of %s)";

    public InvalidCredentialsException() {

    }

    public InvalidCredentialsException(int wrongAttempts, int permittedWrongAttempts) {
        super(String.format(MESSAGE, wrongAttempts, permittedWrongAttempts));
    }

    public InvalidCredentialsException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
