package com.khesam.redis.service;

import com.khesam.redis.service.domain.model.sessionmanagement.User;
import com.khesam.redis.service.dto.sessionmanagement.UserInfoResponse;
import com.khesam.redis.service.exception.InvalidCredentials;
import com.khesam.redis.service.port.input.UserService;
import com.khesam.redis.service.port.output.SuspensionRepository;
import com.khesam.redis.service.port.output.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SuspensionRepository suspensionRepository;

    @Inject
    public UserServiceImpl(
            UserRepository userRepository,
            SuspensionRepository suspensionRepository
    ) {
        this.userRepository = userRepository;
        this.suspensionRepository = suspensionRepository;
    }

    @Override
    public User login(String username, String password) {
        try {
            User user = userRepository.login(username, password);
            suspensionRepository.clearWrongAttempt(username);
            return user;
        } catch (InvalidCredentials ex) {
            suspensionRepository.addWrongAttempt(username);
            throw ex;
        }
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
