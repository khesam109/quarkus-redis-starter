package com.khesam.redis.service.port.output;

public interface SuspensionConfigRepository {

    int suspensionThreshold();
    int suspensionDuration();
}
