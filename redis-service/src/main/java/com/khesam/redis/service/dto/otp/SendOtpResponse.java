package com.khesam.redis.service.dto.otp;

import java.time.ZonedDateTime;
import java.util.UUID;

public record SendOtpResponse(
        UUID trackingId,
        ZonedDateTime createdAt
) {
}
