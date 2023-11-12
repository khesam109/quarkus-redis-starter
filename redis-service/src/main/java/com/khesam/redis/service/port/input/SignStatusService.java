package com.khesam.redis.service.port.input;

import com.khesam.redis.service.domain.model.sign.SignStatus;

public interface SignStatusService {

    SignStatus getSignStatus(String userId, String trackingCode);
}
