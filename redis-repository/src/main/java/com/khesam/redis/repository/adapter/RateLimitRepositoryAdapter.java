package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.redis.RateLimiterRedisRepository;
import com.khesam.redis.service.port.output.repository.RateLimitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RateLimitRepositoryAdapter implements RateLimitRepository {

    private final RateLimiterRedisRepository rateLimiterRedisRepository;

    @Inject
    public RateLimitRepositoryAdapter(
            RateLimiterRedisRepository rateLimiterRedisRepository
    ) {
        this.rateLimiterRedisRepository = rateLimiterRedisRepository;
    }

    @Override
    public int requestCount(String userId, int currentSecond) {
        return rateLimiterRedisRepository.getRequestCount(
                userId,
                currentSecond
        ).orElse(0);
    }

    @Override
    public void setRequestCount(String userId, int requestCount, int currentSecond) {
        rateLimiterRedisRepository.incrementRequestCount(userId, requestCount, currentSecond);
    }


}
