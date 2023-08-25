package com.khesam.redis.repository.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.SetArgs;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class OtpRedisRepository {

    private static final int OTP_EXPIRATION_IN_SEC = 50;
    private static final String OTP_KEY = "otp:%S";


    private final ValueCommands<String, String> commands;

    @Inject
    public OtpRedisRepository(RedisDataSource dataSource) {
        commands = dataSource.value(String.class);
    }


    public void insertOtp(String id, String password) {
        commands.set(
                buildKey(id),
                password,
                new SetArgs().ex(
                        OTP_EXPIRATION_IN_SEC
                ).nx()
        );
    }

    public Optional<String> selectOtp(String id) {
        return Optional.ofNullable(
                commands.get(
                        buildKey(buildKey(id))
                )
        );
    }

    private String buildKey(String id) {
        return String.format(
                OTP_KEY, id
        );
    }
}
