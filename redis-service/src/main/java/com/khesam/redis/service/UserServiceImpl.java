package com.khesam.redis.service;

import com.khesam.redis.service.domain.model.sessionmanagement.User;
import com.khesam.redis.service.dto.sessionmanagement.UserInfoResponse;
import com.khesam.redis.service.port.input.UserService;
import com.khesam.redis.service.port.output.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Inject
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    @Override
    public UserInfoResponse getInfo(String username) {
        User user = userRepository.getUser(username);
        return new UserInfoResponse(
                user.getFirstname(),
                user.getLastname(),
                user.getId().getValue()
        );
    }
}
