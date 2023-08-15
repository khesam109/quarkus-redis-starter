package com.khesam.redis.service.domain.model;

import java.util.EnumSet;

public enum OtpUsage {

    REGISTRATION,
    CHANGE_PASSWORD;

    public static OtpUsage getUsage(String usage) {
        return EnumSet.allOf(OtpUsage.class)
                .stream()
                .filter(e -> e.name().equalsIgnoreCase(usage))
                .findAny().orElseThrow(
                        () -> new IllegalArgumentException("Invalid usage, cannot convert to OtpUsage")
                );
    }
}
