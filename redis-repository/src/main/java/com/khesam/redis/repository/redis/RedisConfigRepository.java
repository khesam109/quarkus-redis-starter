package com.khesam.redis.repository.redis;

import com.khesam.redis.service.port.output.SuspensionConfigRepository;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RedisConfigRepository implements SuspensionConfigRepository {

    private static final String SUSPENSION_THRESHOLD_CONFIG_KEY = "app:config:suspension:threshold";
    private static final String SUSPENSION_DURATION_CONFIG_KEY = "app:config:suspension:duration:min";

    private final ValueCommands<String, String> commands;

    @Inject
    public RedisConfigRepository(RedisDataSource dataSource) {
        commands = dataSource.value(String.class);
    }

    @Override
    public int suspensionThreshold() {
        return Integer.parseInt(
                commands.get(SUSPENSION_THRESHOLD_CONFIG_KEY)
        );
    }

    @Override
    public int suspensionDuration() {
        return Integer.parseInt(
                commands.get(SUSPENSION_DURATION_CONFIG_KEY)
        );
    }
}
