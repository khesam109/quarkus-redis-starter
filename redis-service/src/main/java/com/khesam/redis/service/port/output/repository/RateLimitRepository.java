package com.khesam.redis.service.port.output.repository;

public interface RateLimitRepository {

    int requestCount(String userId, int currentSecond);
    void setRequestCount(String userId, int requestCount, int currentSecond);
}
