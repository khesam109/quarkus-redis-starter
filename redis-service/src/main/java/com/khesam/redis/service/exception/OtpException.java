package com.khesam.redis.service.exception;

public class OtpException extends DomainException {

    public OtpException(String message) {
        super(message);
    }

    public OtpException(String message, Throwable cause) {
        super(message, cause);
    }
}
