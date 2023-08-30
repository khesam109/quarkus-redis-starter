package com.khesam.redis.service.dto.sign;

import java.io.Serializable;

public record CreateSignRequestResponse(
        long signTrackingCode
) implements Serializable {
}
