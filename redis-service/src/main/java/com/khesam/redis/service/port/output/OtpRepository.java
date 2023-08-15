package com.khesam.redis.service.port.output;

import com.khesam.redis.service.domain.model.Otp;
import com.khesam.redis.service.domain.valueobject.OtpId;

import java.util.Optional;

public interface OtpRepository {

    void saveOtp(Otp otp);
    Optional<Otp> getOtp(OtpId otpId);
}
