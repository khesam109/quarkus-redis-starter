package com.khesam.redis.repository.redis;

import com.khesam.redis.service.port.output.SuspensionRepository;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.inject.Inject;

public class SuspensionRedisRepository implements SuspensionRepository {

    private final ValueCommands<String, Integer> commands;

    @Inject
    public SuspensionRedisRepository(RedisDataSource dataSource) {
        commands = dataSource.value(Integer.class);
    }

    @Override
    public void addWrongAttempt(String username) {
        commands.incr()
    }

    @Override
    public void clearWrongAttempt(String username) {

    }
}
