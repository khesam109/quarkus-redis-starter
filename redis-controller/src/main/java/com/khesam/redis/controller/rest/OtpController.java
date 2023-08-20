package com.khesam.redis.controller.rest;

import com.khesam.redis.service.dto.SendOtpCommand;
import com.khesam.redis.service.dto.VerifyOtpCommand;
import com.khesam.redis.service.port.input.OtpService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class OtpController implements OtpRestResource {

    private final OtpService otpService;

    @Inject
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @Override
    public Response sendOtp(SendOtpCommand sendOtpCommand) {
        return Response.ok(
                otpService.createOtp(sendOtpCommand)
        ).build();
    }

    @Override
    public Response verifyOtp(String trackingCode, VerifyOtpCommand verifyOtpCommand) {
        return Response.status(Response.Status.NOT_IMPLEMENTED).build();
    }
}
