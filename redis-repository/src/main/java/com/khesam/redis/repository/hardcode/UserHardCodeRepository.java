package com.khesam.redis.repository.hardcode;

import com.khesam.redis.repository.entity.UserEntity;
import com.khesam.redis.repository.mapper.UserMapper;
import com.khesam.redis.service.domain.model.sessionmanagement.User;
import com.khesam.redis.service.exception.InvalidCredentialsException;
import com.khesam.redis.service.exception.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class UserHardCodeRepository {

    private final UserMapper userMapper;
    private final Map<String, UserEntity> users;

    @Inject
    public UserHardCodeRepository(
            UserMapper userMapper,
            Map<String, UserEntity> users
    ) {
        this.userMapper = userMapper;
        this.users = users;
    }

    public User login(String username, String password) {
        UserEntity user = selectUser(username);

        if (user.getPassword().equals(password)) {
            return userMapper.fromUserEntity(user);
        } else {
            throw new InvalidCredentialsException();
        }
    }

    public User getUser(String username) {
        UserEntity user = selectUser(username);
        return userMapper.fromUserEntity(user);
    }

    private UserEntity selectUser(String username) {
        UserEntity user = users.get(username);
        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }
}
