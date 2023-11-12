package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.redis.RateLimitReportRedisRepository;
import com.khesam.redis.service.port.output.repository.RateLimitReportRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class RateLimitReportRepositoryAdapter implements RateLimitReportRepository {

    private final RateLimitReportRedisRepository rateLimitReportRedisRepository;

    @Inject
    public RateLimitReportRepositoryAdapter(
            RateLimitReportRedisRepository rateLimitReportRedisRepository
    ) {
        this.rateLimitReportRedisRepository = rateLimitReportRedisRepository;
    }

    @Override
    public Map<String, Integer> getReportData(int currentSecond) {
        return rateLimitReportRedisRepository.getReportData(
                currentSecond
        );
    }
}
