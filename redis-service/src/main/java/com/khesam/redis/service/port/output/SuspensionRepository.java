package com.khesam.redis.service.port.output;

public interface SuspensionRepository {

    void addWrongAttempt(String username);
    void clearWrongAttempt(String username);
}
