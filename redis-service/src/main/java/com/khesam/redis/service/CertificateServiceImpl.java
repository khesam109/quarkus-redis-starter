package com.khesam.redis.service;

import com.khesam.redis.service.domain.model.certificate.Certificate;
import com.khesam.redis.service.domain.valueobject.CertificateId;
import com.khesam.redis.service.dto.certificate.IssueCertificateCommand;
import com.khesam.redis.service.event.IssueCertificateEvent;
import com.khesam.redis.service.port.input.CertificateService;
import com.khesam.redis.service.port.output.publisher.IssueCertificateRequestPublisher;
import com.khesam.redis.service.port.output.repository.CertificateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.UUID;

@ApplicationScoped
public class CertificateServiceImpl implements CertificateService {

    private final CertificateRepository certificateRepository;
    private final IssueCertificateRequestPublisher issueCertificateRequestPublisher;

    @Inject
    public CertificateServiceImpl(
            CertificateRepository certificateRepository,
            IssueCertificateRequestPublisher issueCertificateRequestPublisher
    ) {
        this.certificateRepository = certificateRepository;
        this.issueCertificateRequestPublisher = issueCertificateRequestPublisher;
    }

    @Override
    public String issueCertificate(IssueCertificateCommand issueCertificateCommand) {
        Certificate certificate = new Certificate();
        certificate.createCertificate(issueCertificateCommand.csr());
        certificateRepository.saveCertificate(certificate);
        issueCertificateRequestPublisher.publish(
                new IssueCertificateEvent(certificate)
        );

        return certificate.getId().getValue().toString();
    }


    @Override
    public String trackCertificate(String id) {
        Certificate certificate = certificateRepository.getCertificate(
                new CertificateId(UUID.fromString(id))
        );

        return certificate.getStatus().name();
    }
}
