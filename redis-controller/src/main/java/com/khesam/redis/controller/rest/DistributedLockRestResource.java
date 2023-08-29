package com.khesam.redis.controller.rest;

import com.khesam.redis.service.dto.sign.SignCommand;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/redis/use-cases/distributed-lock/sign")
public interface DistributedLockRestResource {

    @POST
    @Path("/{signTrackingCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response sign(
            @PathParam("signTrackingCode") String signTrackingCode,
            SignCommand signCommand
    );
}
