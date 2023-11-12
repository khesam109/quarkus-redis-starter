package com.khesam.redis.controller.rest;

import com.khesam.redis.service.dto.sign.CreateSignRequestCommand;
import com.khesam.redis.service.dto.sign.SignCommand;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/redis/use-cases")
public interface SignRestResource {

    @POST
    @Path("/global-id/sign")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response createSignRequest(
            CreateSignRequestCommand createSignRequestCommand
    );

    @PUT
    @Path("/distributed-lock/sign/{signTrackingCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response sign(
            @PathParam("signTrackingCode") String signTrackingCode,
            SignCommand signCommand
    );

    @GET
    @Path("/{userId}/sign/{signTrackingCode}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getStatus(
            @PathParam("userId") String userId,
            @PathParam("signTrackingCode") String signTrackingCode
    );
}
