package com.khesam.redis.controller.rest.impl;

import com.khesam.redis.controller.rest.CertificateRestResource;
import com.khesam.redis.service.dto.certificate.IssueCertificateCommand;
import com.khesam.redis.service.port.input.CertificateService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CertificateController implements CertificateRestResource {

    private final CertificateService certificateService;

    @Inject
    public CertificateController(
            CertificateService certificateService
    ) {
        this.certificateService = certificateService;
    }

    @Override
    public Response issueCertificate(IssueCertificateCommand issueCertificateCommand) {
        return Response.ok(
                certificateService.issueCertificate(issueCertificateCommand)
        ).build();
    }

    @Override
    public Response trackCertificate(String id) {
        return Response.ok(
                certificateService.trackCertificate(id)
        ).build();
    }
}
