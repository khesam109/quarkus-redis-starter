package com.khesam.redis.repository.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.TransactionalValueCommands;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@ApplicationScoped
public class RateLimiterRedisRepository {

    private final static String SING_STATUS_REQUEST_COUNT_KEY = "sign_status_req_no:%s:%d";
    private final Integer windowSizeInSec;
    private final RedisDataSource dataSource;
    private final ValueCommands<String, Integer> commands;

    @Inject
    public RateLimiterRedisRepository(
            @ConfigProperty(name = "rate.limit.window.size.second") Integer windowSizeInSec,
            RedisDataSource dataSource
    ) {
        this.windowSizeInSec = windowSizeInSec;
        this.dataSource = dataSource;
        commands = dataSource.value(Integer.class);
    }

    public Optional<Integer> getRequestCount(String userId, int currentSecond) {
        return Optional.ofNullable(
                commands.get(
                        buildKey(userId, currentSecond)
                )
        );
    }

    public void incrementRequestCount(String userId, int requestCount, int currentSecond) {
        dataSource.withTransaction(tx -> {
            TransactionalValueCommands<String, Integer> transactionalValueCommands = tx.value(Integer.class);
            transactionalValueCommands.setex(
                    buildKey(userId, currentSecond),
                    windowSizeInSec,
                    requestCount
            );
        });
    }

    private String buildKey(String userId, int currentSecond) {
        return String.format(
                SING_STATUS_REQUEST_COUNT_KEY, userId, currentSecond / windowSizeInSec
        );
    }
}
