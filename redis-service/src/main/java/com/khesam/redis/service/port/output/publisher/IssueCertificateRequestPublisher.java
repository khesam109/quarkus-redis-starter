package com.khesam.redis.service.port.output.publisher;

import com.khesam.redis.service.event.IssueCertificateEvent;

public interface IssueCertificateRequestPublisher {

    void publish(IssueCertificateEvent event);
}
