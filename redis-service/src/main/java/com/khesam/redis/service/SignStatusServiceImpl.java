package com.khesam.redis.service;

import com.khesam.redis.service.domain.model.sign.SignStatus;
import com.khesam.redis.service.port.input.SignStatusService;
import com.khesam.redis.service.ratelimit.SignStatusRateLimiter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SignStatusServiceImpl implements SignStatusService {

    private final SignStatusRateLimiter signStatusRateLimiter;

    @Inject
    public SignStatusServiceImpl(
            SignStatusRateLimiter signStatusRateLimiter
    ) {
        this.signStatusRateLimiter = signStatusRateLimiter;
    }


    @Override
    public SignStatus getSignStatus(String userId, String trackingCode) {
        signStatusRateLimiter.checkExceed(userId);
        return SignStatus.COMPLETED;
    }
}
