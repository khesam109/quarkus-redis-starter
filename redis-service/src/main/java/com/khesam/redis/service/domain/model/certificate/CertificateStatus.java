package com.khesam.redis.service.domain.model.certificate;

import java.util.EnumSet;

public enum CertificateStatus {

    PENDING,
    ISSUED,
    REJECTED;

    public static CertificateStatus getStatus(String status) {
        return EnumSet.allOf(CertificateStatus.class)
                .stream()
                .filter(e -> e.name().equalsIgnoreCase(status))
                .findAny().orElseThrow(
                        () -> new IllegalArgumentException("Invalid status, cannot convert to CertificateStatus")
                );
    }
}
