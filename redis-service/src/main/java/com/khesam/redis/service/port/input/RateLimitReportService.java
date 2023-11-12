package com.khesam.redis.service.port.input;

import java.util.Map;

public interface RateLimitReportService {

    Map<String, Integer> getReportData();
}
