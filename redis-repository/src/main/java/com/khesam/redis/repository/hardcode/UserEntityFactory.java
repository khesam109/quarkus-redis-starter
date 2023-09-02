package com.khesam.redis.repository.hardcode;

import com.khesam.redis.repository.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class UserEntityFactory {

    public List<UserEntity> getDummyUsers() {
        UserEntity admin = UserEntity.Builder.builder()
                .nationalCode("0011836482")
                .username("admin")
                .password("admin")
                .firstname("Asghar")
                .lastname("Davaloo")
                .build();

        UserEntity mamooli = UserEntity.Builder.builder()
                .nationalCode("0123456789")
                .username("mamooli")
                .password("admin")
                .firstname("Akbar")
                .lastname("Mamooli")
                .build();

        return Arrays.asList(admin, mamooli);
    }
}
