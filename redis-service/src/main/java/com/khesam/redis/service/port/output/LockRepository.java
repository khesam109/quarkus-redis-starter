package com.khesam.redis.service.port.output;

public interface LockRepository {

    boolean acquireLock(String signTrackingCode, String signerId);
    void releaseLock(String signTrackingCode);
}
