package com.khesam.redis.controller.rest;

import com.khesam.redis.service.dto.SendOtpCommand;
import com.khesam.redis.service.dto.VerifyOtpCommand;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class OtpController implements OtpRestResource {

    @Override
    public Response sendOtp(SendOtpCommand sendOtpCommand, String usage) {
        return null;
    }

    @Override
    public Response verifyOtp(String trackingCode, VerifyOtpCommand verifyOtpCommand) {
        return null;
    }
}
