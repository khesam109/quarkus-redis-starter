package com.khesam.redis.controller.rest.impl;

import com.khesam.redis.controller.rest.SignRestResource;
import com.khesam.redis.service.dto.sign.CreateSignRequestCommand;
import com.khesam.redis.service.dto.sign.SignCommand;
import com.khesam.redis.service.exception.IllegalConcurrentAccessException;
import com.khesam.redis.service.port.input.SignService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class SignController implements SignRestResource {

    private final SignService signService;

    @Inject
    public SignController(
            SignService signService
    ) {
        this.signService = signService;
    }

    @Override
    public Response createSignRequest(
            CreateSignRequestCommand createSignRequestCommand
    ) {
        return Response.accepted(
                signService.createSignRequest(createSignRequestCommand.tbs())
        ).build();
    }

    @Override
    public Response sign(String signTrackingCode, SignCommand signCommand) {
        try {
            signService.sign(signCommand.signerId(), signTrackingCode);
            return Response.status(Response.Status.OK).build();
        } catch (IllegalConcurrentAccessException ex) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
