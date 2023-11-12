package com.khesam.redis.controller.rest.impl;

import com.khesam.redis.controller.rest.ReportRestResource;
import com.khesam.redis.service.port.input.RateLimitReportService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ReportController implements ReportRestResource {

    private final RateLimitReportService rateLimitReportService;

    @Inject
    public ReportController(RateLimitReportService rateLimitReportService) {
        this.rateLimitReportService = rateLimitReportService;
    }

    @Override
    public Response getStatus() {
        return Response.ok(
                rateLimitReportService.getReportData()
        ).build();
    }
}
