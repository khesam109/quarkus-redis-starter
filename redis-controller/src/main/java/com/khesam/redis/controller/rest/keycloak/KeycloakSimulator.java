package com.khesam.redis.controller.rest.keycloak;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.khesam.redis.controller.rest.exception.InvalidAccessTokenException;
import com.khesam.redis.service.domain.model.sessionmanagement.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@ApplicationScoped
public class KeycloakSimulator {

    private static final String CLAIM_KEY_FIRSTNAME = "firstname";
    private static final String CLAIM_KEY_LASTNAME = "lastname";
    private static final int JWT_AGE_IN_DAY = 1;


    private final Algorithm algorithm;
    private final JWTVerifier jwtVerifier;
    private final String issuer;

    @Inject
    public KeycloakSimulator(
            Algorithm algorithm,
            JWTVerifier jwtVerifier,
            @ConfigProperty(name = "jwt.issuer") String issuer
    ) {
        this.algorithm = algorithm;
        this.jwtVerifier = jwtVerifier;
        this.issuer = issuer;
    }

    public String issueJwt(User user) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(user.getUsername())
                .withClaim(CLAIM_KEY_FIRSTNAME, user.getFirstname())
                .withClaim(CLAIM_KEY_LASTNAME, user.getLastname())
                .withIssuedAt(Instant.now())
                .withNotBefore(Instant.now())
                .withExpiresAt(Instant.now().plus(JWT_AGE_IN_DAY, ChronoUnit.DAYS))
                .withJWTId(UUID.randomUUID().toString())
                .sign(algorithm);
    }

    public DecodedJWT verifyJwt(String jwt) {
        try {
            return jwtVerifier.verify(jwt);
        } catch (JWTVerificationException e) {
            throw new InvalidAccessTokenException("Invalid Access Token", e);
        }
    }
}
