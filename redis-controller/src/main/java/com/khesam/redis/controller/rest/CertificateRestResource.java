package com.khesam.redis.controller.rest;

import com.khesam.redis.service.dto.certificate.IssueCertificateCommand;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/redis/use-cases/publish-subscribe/certificate")
public interface CertificateRestResource {

    @POST
    @Path("/issue")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    Response issueCertificate(IssueCertificateCommand issueCertificateCommand);

    @GET
    @Path("/{id}/status")
    @Produces(MediaType.TEXT_PLAIN)
    Response trackCertificate(@PathParam("id") String id);
}
