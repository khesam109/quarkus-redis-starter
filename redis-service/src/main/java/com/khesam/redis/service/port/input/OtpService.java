package com.khesam.redis.service.port.input;

import com.khesam.redis.service.dto.SendOtpCommand;
import com.khesam.redis.service.dto.SendOtpResponse;

public interface OtpService {

    SendOtpResponse createOtp(SendOtpCommand sendOtpCommand);
}
