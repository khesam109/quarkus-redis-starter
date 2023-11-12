package com.khesam.redis.controller.rest.impl;

import com.khesam.redis.controller.rest.SignRestResource;
import com.khesam.redis.service.dto.sign.CreateSignRequestCommand;
import com.khesam.redis.service.dto.sign.SignCommand;
import com.khesam.redis.service.exception.IllegalConcurrentAccessException;
import com.khesam.redis.service.port.input.SignService;
import com.khesam.redis.service.port.input.SignStatusService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class SignController implements SignRestResource {

    private final SignService signService;
    private final SignStatusService signStatusService;

    @Inject
    public SignController(
            SignService signService,
            SignStatusService signStatusService
    ) {
        this.signService = signService;
        this.signStatusService = signStatusService;
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

    @Override
    public Response getStatus(String userId, String signTrackingCode) {
        try {
            return Response.ok(
                    signStatusService.getSignStatus(userId, signTrackingCode)
            ).build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.TOO_MANY_REQUESTS).build();
        }
    }
}
