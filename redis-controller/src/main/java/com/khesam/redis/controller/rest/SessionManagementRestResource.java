package com.khesam.redis.controller.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestHeader;

@Path("/redis/use-cases/session-management")
public interface SessionManagementRestResource {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Response login(
            MultivaluedMap<String, String> usernamePassword
    );

    @GET
    @Path("/user/info")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserInfo(
            @RestHeader("Authorization") String jwt
    );
}
