package com.khesam.redis.repository.mapper;

import com.khesam.redis.repository.entity.UserEntity;
import com.khesam.redis.service.domain.model.sessionmanagement.User;
import com.khesam.redis.service.domain.valueobject.UserId;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserMapper {

    public User fromUserEntity(UserEntity userEntity) {
        return User.Builder.builder()
                .userId(new UserId(userEntity.getNationalCode()))
                .username(userEntity.getUsername())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .build();
    }

    public UserEntity toUserEntity(User user) {
        return UserEntity.Builder.builder()
                .nationalCode(user.getId().getValue())
                .username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .build();
    }
}
