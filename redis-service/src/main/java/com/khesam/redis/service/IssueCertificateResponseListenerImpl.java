package com.khesam.redis.service;

import com.khesam.redis.service.domain.model.certificate.Certificate;
import com.khesam.redis.service.domain.valueobject.CertificateId;
import com.khesam.redis.service.port.input.message.IssueCertificateResponseListener;
import com.khesam.redis.service.port.output.repository.CertificateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class IssueCertificateResponseListenerImpl implements IssueCertificateResponseListener {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    private final CertificateRepository certificateRepository;

    @Inject
    public IssueCertificateResponseListenerImpl(
            CertificateRepository certificateRepository
    ) {
        this.certificateRepository = certificateRepository;
    }

    @Override
    public void completed(String id) {
        executorService.execute(() -> {
            Certificate certificate = certificateRepository.getCertificate(
                    new CertificateId(UUID.fromString(id))
            );
            certificate.issue();

            certificateRepository.updateCertificate(certificate);
        });

//        Certificate certificate = certificateRepository.getCertificate(
//                new CertificateId(UUID.fromString(id))
//        );
//        certificate.issue();
//
//        certificateRepository.updateCertificate(certificate);
    }

    @Override
    public void failed(String id) {
        executorService.execute(() -> {
            Certificate certificate = certificateRepository.getCertificate(
                    new CertificateId(UUID.fromString(id))
            );
            certificate.reject();

            certificateRepository.updateCertificate(certificate);
        });

//        Certificate certificate = certificateRepository.getCertificate(
//                new CertificateId(UUID.fromString(id))
//        );
//        certificate.reject();
//
//        certificateRepository.updateCertificate(certificate);
    }
}
