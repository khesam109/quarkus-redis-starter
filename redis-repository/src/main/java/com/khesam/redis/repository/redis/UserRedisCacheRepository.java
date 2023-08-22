package com.khesam.redis.repository.redis;

import com.khesam.redis.repository.mapper.UserMapper;
import com.khesam.redis.repository.serializer.RedisCustomSerializer;
import com.khesam.redis.service.domain.model.sessionmanagement.User;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UserRedisCacheRepository {

    private static final int USER_EXPIRATION_IN_SEC = 120;

    private final ValueCommands<String, String> commands;
    private final RedisCustomSerializer redisCustomSerializer;
    private final UserMapper userMapper;

    @Inject
    public UserRedisCacheRepository(
            RedisDataSource dataSource,
            RedisCustomSerializer redisCustomSerializer,
            UserMapper userMapper
    ) {
        commands = dataSource.value(String.class);
        this.redisCustomSerializer = redisCustomSerializer;
        this.userMapper = userMapper;
    }

    public void insertUser(User user) {
        commands.set(
                user.getUsername(),
                redisCustomSerializer.serializerUserEntity(
                        userMapper.toUserEntity(user)
                )
        );
    }

    public Optional<User> selectUser(String username) {
        Optional<String> userEntity = Optional.ofNullable(
                commands.get(username)
        );

        return userEntity.map(
                redisCustomSerializer::deserializeUserEntity
        ).map(
                userMapper::fromUserEntity
        );
    }
}
