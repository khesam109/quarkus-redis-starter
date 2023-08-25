package com.khesam.redis.service.port.output;

public interface SuspensionRepository {

    boolean isSuspended(String username);
    long addWrongAttempt(String username);
    void clearWrongAttempt(String username);
    void suspendUser(String username);
}
