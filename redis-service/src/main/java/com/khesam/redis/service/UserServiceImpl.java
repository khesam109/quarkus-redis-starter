package com.khesam.redis.service;

import com.khesam.redis.service.domain.model.sessionmanagement.User;
import com.khesam.redis.service.dto.sessionmanagement.UserInfoResponse;
import com.khesam.redis.service.exception.InvalidCredentialsException;
import com.khesam.redis.service.exception.SuspendedUserException;
import com.khesam.redis.service.port.input.UserService;
import com.khesam.redis.service.port.output.repository.SuspensionConfigRepository;
import com.khesam.redis.service.port.output.repository.SuspensionRepository;
import com.khesam.redis.service.port.output.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SuspensionRepository suspensionRepository;
    private final SuspensionConfigRepository suspensionConfigRepository;

    @Inject
    public UserServiceImpl(
            UserRepository userRepository,
            SuspensionRepository suspensionRepository,
            SuspensionConfigRepository suspensionConfigRepository
    ) {
        this.userRepository = userRepository;
        this.suspensionRepository = suspensionRepository;
        this.suspensionConfigRepository = suspensionConfigRepository;
    }

    @Override
    public User login(String username, String password) {
        checkUserSuspension(username);
        return tryToLogin(username, password);
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

    private void checkUserSuspension(String username) {
        if (suspensionRepository.isSuspended(username)) {
            throw new SuspendedUserException(username);
        }
    }

    private User tryToLogin(String username, String password) {
        try {
            User user = userRepository.login(username, password);
            suspensionRepository.clearWrongAttempt(username);
            return user;
        } catch (InvalidCredentialsException ex) {
            throw new InvalidCredentialsException(
                    (int) addWrongAttempts(username),
                    suspensionConfigRepository.suspensionThreshold()
            );
        }
    }

    private long addWrongAttempts(String username) {
        long wrongAttempts = suspensionRepository.addWrongAttempt(username);
        if (wrongAttempts == suspensionConfigRepository.suspensionThreshold()) {
            suspensionRepository.suspendUser(username);
        }
        return wrongAttempts;
    }
}
