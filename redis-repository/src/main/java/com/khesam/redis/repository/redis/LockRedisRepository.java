package com.khesam.redis.repository.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LockRedisRepository {

    private static final String SIGN_TRACKING_CODE_LOCK_KEY = "sign:%s:lock";

    private final ValueCommands<String, String> commands;

    @Inject
    public LockRedisRepository(RedisDataSource dataSource) {
        commands = dataSource.value(String.class);
    }

    public boolean setLock(String id, String value) {
        return commands.setnx(
                buildKey(id), value
        );
    }

    public void deleteLock(String id) {
        commands.getdel(
                buildKey(id)
        );
    }

    private String buildKey(String id) {
        return String.format(
                SIGN_TRACKING_CODE_LOCK_KEY, id
        );
    }
}
