package com.khesam.redis.service.event;

import com.khesam.redis.service.domain.model.certificate.Certificate;

public record IssueCertificateEvent(
        Certificate certificate
) {
}
