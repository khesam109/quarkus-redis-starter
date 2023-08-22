package com.khesam.redis.controller.rest.impl;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.khesam.redis.controller.rest.SessionManagementRestResource;
import com.khesam.redis.controller.rest.keycloak.KeycloakSimulator;
import com.khesam.redis.service.domain.model.sessionmanagement.User;
import com.khesam.redis.service.dto.sessionmanagement.LoginResponse;
import com.khesam.redis.service.port.input.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class SessionManagementController implements SessionManagementRestResource {

    private final UserService userService;
    private final KeycloakSimulator keycloakSimulator;
    @Inject
    public SessionManagementController(
            UserService userService,
            KeycloakSimulator keycloakSimulator
    ) {
        this.userService = userService;
        this.keycloakSimulator = keycloakSimulator;
    }

    @Override
    public Response login(MultivaluedMap<String, String> usernamePassword) {
        String username = usernamePassword.getFirst("username");
        String password = usernamePassword.getFirst("password");

        User user = userService.login(username, password);

        return Response.ok(
                new LoginResponse(
                        keycloakSimulator.issueJwt(user)
                )
        ).build();
    }

    @Override
    public Response getUserInfo(String authorizationToken) {
        DecodedJWT decodedJWT = keycloakSimulator.verifyJwt(extractJwt(authorizationToken));

        return Response.ok(
                userService.getInfo(decodedJWT.getSubject())
        ).build();
    }

    private String extractJwt(String authorizationToken) {
        return authorizationToken.replace(
                "Bearer ", ""
        );
    }
}
