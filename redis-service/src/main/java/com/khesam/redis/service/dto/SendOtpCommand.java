package com.khesam.redis.service.dto;

import java.io.Serializable;

public record SendOtpCommand(
        String mobileNumber
) implements Serializable {

}
