package com.khesam.redis.repository.di;

import com.khesam.redis.repository.entity.UserEntity;
import com.khesam.redis.repository.hardcode.UserEntityFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import java.util.HashMap;
import java.util.Map;


public class UsersProvider {

    @Produces
    @ApplicationScoped
    public Map<String, UserEntity> provideUsers(
            UserEntityFactory userEntityFactory
    ) {
        Map<String, UserEntity> users = new HashMap<>();
        userEntityFactory.getDummyUsers().forEach(e -> users.put(e.getUsername(), e));

        return users;
    }

}
