package com.khesam.redis.service.port.input;

import com.khesam.redis.service.dto.sign.CreateSignRequestResponse;

public interface SignService {

    CreateSignRequestResponse createSignRequest(String tbs);
    void sign(String signerId, String signTrack);
}
