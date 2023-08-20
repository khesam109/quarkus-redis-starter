package com.khesam.redis.repository.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class OtpRedisRepository {

    private static final int OTP_EXPIRATION_IN_SEC = 10;

    private final ValueCommands<String, String> commands;

    @Inject
    public OtpRedisRepository(RedisDataSource dataSource) {
        commands = dataSource.value(String.class);
    }


    public void insertOtp(String mobileNumber, String password) {
        commands.setex(mobileNumber, OTP_EXPIRATION_IN_SEC, password);
    }
}
