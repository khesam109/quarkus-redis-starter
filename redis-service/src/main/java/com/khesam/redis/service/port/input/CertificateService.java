package com.khesam.redis.service.port.input;

import com.khesam.redis.service.dto.certificate.IssueCertificateCommand;

public interface CertificateService {

    String issueCertificate(IssueCertificateCommand issueCertificateCommand);
    String trackCertificate(String id);
}
