package com.khesam.redis.service.dto.sign;

import java.io.Serializable;

public record SignCommand(
        String signerId
) implements Serializable {
}
