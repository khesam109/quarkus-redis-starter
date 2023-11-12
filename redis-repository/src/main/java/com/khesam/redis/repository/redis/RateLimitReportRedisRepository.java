package com.khesam.redis.repository.redis;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class RateLimitReportRedisRepository {

    private final static String SING_STATUS_REQUEST_COUNT_KEY_PATTERN = "sign_status_req_no:*:%d";

    private final Integer windowSizeInSec;
    private final RedisDataSource dataSource;
    private final ValueCommands<String, Integer> commands;

    @Inject
    public RateLimitReportRedisRepository(
            @ConfigProperty(name = "rate.limit.window.size.second") Integer windowSizeInSec,
            RedisDataSource dataSource
    ) {
        this.windowSizeInSec = windowSizeInSec;
        this.dataSource = dataSource;
        commands = dataSource.value(Integer.class);
    }

    public Map<String, Integer> getReportData(int currentSecond) {
        List<String> keys = getKeys(currentSecond);
        return keys.stream().collect(Collectors.toMap(
                this::extractUsernameFromKey,
                this::getReportDataByUserId)
        );
    }

    private Integer getReportDataByUserId(String key) {
        return Optional.ofNullable(
                commands.get(key)
        ).orElse(0);
    }

    private List<String> getKeys(int currentSecond) {
        return dataSource.key().keys(
                buildKeyPattern(currentSecond)
        );
    }

    private String extractUsernameFromKey(String key) {
        return key.split(":")[1];
    }

    private String buildKeyPattern(int currentSecond) {
        return String.format(
                SING_STATUS_REQUEST_COUNT_KEY_PATTERN, currentSecond / windowSizeInSec
        );
    }
}
