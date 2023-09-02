package com.khesam.redis.service.port.input.message;

public interface IssueCertificateResponseListener {

    void completed(String id);
    void failed(String id);
}
