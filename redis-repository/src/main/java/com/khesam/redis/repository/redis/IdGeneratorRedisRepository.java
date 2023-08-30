package com.khesam.redis.repository.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IdGeneratorRedisRepository {

    private static final String GLOBAL_SIGN_REQUEST_ID_KEY = "global_id:sign_request";

    private final ValueCommands<String, Long> commands;

    @Inject
    public IdGeneratorRedisRepository(RedisDataSource dataSource) {
        commands = dataSource.value(Long.class);
    }

    public long getGlobalSignRequestId() {
        return commands.incr(
                GLOBAL_SIGN_REQUEST_ID_KEY
        );
    }
}
