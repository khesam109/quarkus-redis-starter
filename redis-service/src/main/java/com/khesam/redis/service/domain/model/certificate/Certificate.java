package com.khesam.redis.service.domain.model.certificate;

import com.khesam.redis.service.domain.model.BaseModel;
import com.khesam.redis.service.domain.valueobject.CertificateId;

import java.util.UUID;


public class Certificate extends BaseModel<CertificateId> {

    private String csr;
    private CertificateStatus status;

    public void createCertificate(String csr) {
        setId(new CertificateId(UUID.randomUUID()));
        this.csr = csr;
        status = CertificateStatus.PENDING;
    }

    public void issue() {
        status = CertificateStatus.ISSUED;
    }

    public void reject() {
        status = CertificateStatus.REJECTED;
    }

    public String getCsr() {
        return csr;
    }

    public void setCsr(String csr) {
        this.csr = csr;
    }

    public CertificateStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = CertificateStatus.getStatus(status);
    }
}
