package com.khesam.redis.service.dto.otp;

import java.io.Serializable;

public record SendOtpCommand(
        String mobileNumber
) implements Serializable {

}
