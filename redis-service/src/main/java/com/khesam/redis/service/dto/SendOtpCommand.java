package com.khesam.redis.service.dto;

import java.io.Serializable;

public record SendOtpCommand(
        String usage,
        String mobileNumber
) implements Serializable {

}
