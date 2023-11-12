package com.khesam.redis.service;

import com.khesam.redis.service.port.input.RateLimitReportService;
import com.khesam.redis.service.port.output.repository.RateLimitReportRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.Map;

@ApplicationScoped
public class RateLimitReportServiceImpl implements RateLimitReportService {


    private final RateLimitReportRepository rateLimitReportRepository;

    @Inject
    public RateLimitReportServiceImpl(
            RateLimitReportRepository rateLimitReportRepository
    ) {
        this.rateLimitReportRepository = rateLimitReportRepository;
    }

    @Override
    public Map<String, Integer> getReportData() {
        return rateLimitReportRepository.getReportData(
                LocalDateTime.now().getSecond()
        );
    }
}
