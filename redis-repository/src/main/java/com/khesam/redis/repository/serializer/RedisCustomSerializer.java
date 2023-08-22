package com.khesam.redis.repository.serializer;

import com.khesam.redis.repository.hardcode.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisCustomSerializer {

    public String serializerUserEntity(UserEntity userEntity) {
        return userEntity.getNationalCode() + ":" +
                userEntity.getUsername() + ":" +
                userEntity.getFirstname() + ":" +
                userEntity.getLastname();
    }

    public UserEntity deserializeUserEntity(String text) {
        String[] values = text.split(":");
        return UserEntity.Builder.builder()
                .nationalCode(values[0])
                .username(values[1])
                .firstname(values[2])
                .lastname(values[3])
                .build();
    }
}
