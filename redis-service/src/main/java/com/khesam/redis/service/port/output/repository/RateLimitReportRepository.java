package com.khesam.redis.service.port.output.repository;

import java.util.Map;

public interface RateLimitReportRepository {

    Map<String, Integer> getReportData(int currentSecond);
}
