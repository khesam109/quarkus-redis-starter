package com.khesam.redis.controller.rest;

import com.khesam.redis.service.dto.otp.SendOtpCommand;
import com.khesam.redis.service.dto.otp.VerifyOtpCommand;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/redis/use-cases/db/otp")
public interface OtpRestResource {

    @POST
    @Path("/send")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response sendOtp(
            SendOtpCommand sendOtpCommand
    );

    @POST
    @Path("/verify/{trackingCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response verifyOtp(
            @PathParam("trackingCode") String trackingCode,
            VerifyOtpCommand verifyOtpCommand
    );
}
