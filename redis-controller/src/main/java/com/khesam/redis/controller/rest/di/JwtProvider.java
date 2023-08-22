package com.khesam.redis.controller.rest.di;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

public class JwtProvider {


    @Produces
    @ApplicationScoped
    public Algorithm provideAlgorithm(
            @ConfigProperty(name = "jwt.secret") String secret
    ) {
        return Algorithm.HMAC256(secret);
    }

    @Produces
    @ApplicationScoped
    public JWTVerifier provideJwtVerifier(
            Algorithm algorithm,
            @ConfigProperty(name = "jwt.issuer") String issuer
    ) {
        return JWT.require(algorithm)
                .withIssuer(issuer)
                .build();
    }
}
