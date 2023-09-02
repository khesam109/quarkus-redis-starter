package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.redis.CertificateRedisRepository;
import com.khesam.redis.service.domain.model.certificate.Certificate;
import com.khesam.redis.service.domain.valueobject.CertificateId;
import com.khesam.redis.service.port.output.repository.CertificateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CertificateRepositoryAdapter implements CertificateRepository {

    private final CertificateRedisRepository certificateRedisRepository;

    @Inject
    public CertificateRepositoryAdapter(
            CertificateRedisRepository certificateRedisRepository
    ) {
        this.certificateRedisRepository = certificateRedisRepository;
    }

    @Override
    public void saveCertificate(Certificate certificate) {
        certificateRedisRepository.insertCertificate(certificate);
    }

    @Override
    public Certificate getCertificate(CertificateId certificateId) {
        return certificateRedisRepository.selectCertificate(certificateId);
    }

    @Override
    public void updateCertificate(Certificate certificate) {
        certificateRedisRepository.updateCertificate(certificate);
    }
}
