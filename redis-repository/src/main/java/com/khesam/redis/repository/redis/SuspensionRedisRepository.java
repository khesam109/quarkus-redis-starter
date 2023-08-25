package com.khesam.redis.repository.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.SetArgs;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SuspensionRedisRepository {

    private static final String USER_SUSPENSION_STATUS_KEY = "user:%S:wrong_attempts";
    private static final int USER_SUSPENDED_STATUS_VALUE = -1;
    private final ValueCommands<String, Integer> commands;

    @Inject
    public SuspensionRedisRepository(RedisDataSource dataSource) {
        commands = dataSource.value(Integer.class);
    }

    public boolean isSuspended(String username) {
        return commands.get(
                buildKey(username)
        ) == USER_SUSPENDED_STATUS_VALUE;
    }

    public long addWrongAttempt(String username) {
        return commands.incr(
                buildKey(username)
        );
    }

    public void clearWrongAttempt(String username) {
        commands.set(
                buildKey(username),
                0,
                new SetArgs().xx()
        );
    }

    public void suspendUser(String username) {
        commands.set(
                buildKey(username),
                USER_SUSPENDED_STATUS_VALUE
        );
    }

    private String buildKey(String username) {
        return String.format(
                USER_SUSPENSION_STATUS_KEY, username
        );
    }
}
