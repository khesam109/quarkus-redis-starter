package com.khesam.redis.service.dto.sign;

import java.io.Serializable;

public record CreateSignRequestCommand(
        String tbs
) implements Serializable {
}
