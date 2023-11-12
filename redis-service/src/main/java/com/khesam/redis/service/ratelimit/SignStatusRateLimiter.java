package com.khesam.redis.service.ratelimit;

import com.khesam.redis.service.port.output.repository.RateLimitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;

@ApplicationScoped
public class SignStatusRateLimiter {

    private final Integer requestPerWindow;
    private final RateLimitRepository rateLimitRepository;

    @Inject
    public SignStatusRateLimiter(
            @ConfigProperty(name = "rate.limit.requests.per.window") Integer requestPerWindow,
            RateLimitRepository rateLimitRepository
    ) {
        this.requestPerWindow = requestPerWindow;
        this.rateLimitRepository = rateLimitRepository;
    }

    public void checkExceed(String userId) {
        LocalDateTime now = LocalDateTime.now();
        int requestCount = rateLimitRepository.requestCount(userId, now.getSecond());

        if (requestCount >= requestPerWindow) {
            throw new IllegalArgumentException("Too many request");
        }

        rateLimitRepository.setRequestCount(userId, requestCount + 1, now.getSecond());
    }
}
