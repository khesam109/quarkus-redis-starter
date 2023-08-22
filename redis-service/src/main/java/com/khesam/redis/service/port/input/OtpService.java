package com.khesam.redis.service.port.input;

import com.khesam.redis.service.dto.otp.SendOtpCommand;
import com.khesam.redis.service.dto.otp.SendOtpResponse;

public interface OtpService {

    SendOtpResponse createOtp(SendOtpCommand sendOtpCommand);
    boolean verifyOtp(String trackingCode, String password);
}
