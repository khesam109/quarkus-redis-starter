package com.khesam.redis.service.domain.valueobject;

import java.util.UUID;

public class OtpId extends BaseId<UUID> {

    public OtpId(UUID value) {
        super(value);
    }
}
