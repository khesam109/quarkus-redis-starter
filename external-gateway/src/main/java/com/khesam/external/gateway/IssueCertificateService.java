package com.khesam.external.gateway;

import com.khesam.external.gateway.publisher.IssueCertificateCompletedPublisher;
import com.khesam.external.gateway.publisher.IssueCertificateFailedPublisher;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class IssueCertificateService {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final IssueCertificateCompletedPublisher issueCertificateCompletedPublisher;
    private final IssueCertificateFailedPublisher issueCertificateFailedPublisher;

    @Inject
    public IssueCertificateService(
            IssueCertificateCompletedPublisher issueCertificateCompletedPublisher,
            IssueCertificateFailedPublisher issueCertificateFailedPublisher
    ) {
        this.issueCertificateCompletedPublisher = issueCertificateCompletedPublisher;
        this.issueCertificateFailedPublisher = issueCertificateFailedPublisher;
    }

    public void issueCertificate(String id) {
        boolean result = issueCertificate();
        if (result) {
            executorService.execute(() -> issueCertificateCompletedPublisher.publish(id));
        } else {
            issueCertificateFailedPublisher.publish(id);
        }
    }

    private boolean issueCertificate() {
        return true;
//        Random random = new Random();
//        return random.nextBoolean();
    }
}
