package com.khesam.redis.repository.mapper;

import com.khesam.redis.repository.entity.CertificateEntity;
import com.khesam.redis.service.domain.model.certificate.Certificate;
import com.khesam.redis.service.domain.valueobject.CertificateId;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class CertificateMapper {

    public Certificate fromCertificateEntity(CertificateEntity certificateEntity) {
        Certificate certificate = new Certificate();
        certificate.setId(new CertificateId(UUID.fromString(certificateEntity.getId())));
        certificate.setCsr(certificate.getCsr());
        certificate.setStatus(certificateEntity.getStatus());
        return certificate;
    }
    public CertificateEntity toCertificateEntity(Certificate certificate) {
        return CertificateEntity.Builder.builder()
                .id(certificate.getId().getValue().toString())
                .csr(certificate.getCsr())
                .status(certificate.getStatus().name())
                .build();
    }
}
