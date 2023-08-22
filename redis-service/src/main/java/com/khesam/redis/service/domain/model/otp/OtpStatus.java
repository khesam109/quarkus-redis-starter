package com.khesam.redis.service.domain.model.otp;

import java.util.EnumSet;

public enum OtpStatus {

    PENDING,
    VERIFIED,
    FUCKED_UP,
    EXPIRED
}
