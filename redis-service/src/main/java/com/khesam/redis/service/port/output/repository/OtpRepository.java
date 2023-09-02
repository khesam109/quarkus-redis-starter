package com.khesam.redis.service.port.output.repository;

import com.khesam.redis.service.domain.model.otp.Otp;
import com.khesam.redis.service.domain.valueobject.OtpId;

public interface OtpRepository {

    void saveOtp(Otp otp);
    Otp getOtp(OtpId otpId);
    void removeOtp(OtpId otpId);
}
