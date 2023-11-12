package com.khesam.redis.controller.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/redis/use-cases/report")
public interface ReportRestResource {

    @GET
    @Path("/rate-limit")
    @Produces(MediaType.APPLICATION_JSON)
    Response getStatus();
}
