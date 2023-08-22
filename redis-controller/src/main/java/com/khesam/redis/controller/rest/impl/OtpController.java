package com.khesam.redis.controller.rest.impl;

import com.khesam.redis.controller.rest.OtpRestResource;
import com.khesam.redis.service.dto.otp.SendOtpCommand;
import com.khesam.redis.service.dto.otp.VerifyOtpCommand;
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
        if (otpService.verifyOtp(trackingCode, verifyOtpCommand.password())) {
            return Response.accepted().build();
        } else {
            return Response.notAcceptable(null).build();
        }
    }
}
