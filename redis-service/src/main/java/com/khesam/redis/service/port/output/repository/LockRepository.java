package com.khesam.redis.service.port.output.repository;

public interface LockRepository {

    boolean acquireLock(String signTrackingCode, String signerId);
    void releaseLock(String signTrackingCode);
}
