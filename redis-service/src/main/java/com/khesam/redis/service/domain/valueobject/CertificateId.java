package com.khesam.redis.service.domain.valueobject;

import java.util.UUID;

public class CertificateId extends BaseId<UUID> {

    public CertificateId(UUID value) {
        super(value);
    }
}
