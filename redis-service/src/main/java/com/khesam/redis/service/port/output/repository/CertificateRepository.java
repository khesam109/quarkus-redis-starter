package com.khesam.redis.service.port.output.repository;

import com.khesam.redis.service.domain.model.certificate.Certificate;
import com.khesam.redis.service.domain.valueobject.CertificateId;

public interface CertificateRepository {

    void saveCertificate(Certificate certificate);
    Certificate getCertificate(CertificateId certificateId);
    void updateCertificate(Certificate certificate);
}
