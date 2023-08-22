package com.khesam.redis.service.port.output;

import com.khesam.redis.service.domain.model.otp.Otp;
import com.khesam.redis.service.domain.valueobject.OtpId;

public interface OtpRepository {

    void saveOtp(Otp otp);
    Otp getOtp(OtpId otpId);
}
