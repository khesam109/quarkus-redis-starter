package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.hardcode.UserHardCodeRepository;
import com.khesam.redis.repository.redis.UserRedisCacheRepository;
import com.khesam.redis.service.domain.model.sessionmanagement.User;
import com.khesam.redis.service.port.output.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class UserRepositoryAdapter implements UserRepository {

    private final UserHardCodeRepository userHardCodeRepository;
    private final UserRedisCacheRepository userRedisCacheRepository;

    @Inject
    public UserRepositoryAdapter(
            UserHardCodeRepository userHardCodeRepository,
            UserRedisCacheRepository userRedisCacheRepository
    ) {
        this.userHardCodeRepository = userHardCodeRepository;
        this.userRedisCacheRepository = userRedisCacheRepository;
    }

    @Override
    public User login(String username, String password) {
        return userHardCodeRepository.login(username, password);
    }

    @Override
    public User getUser(String username) {
        Optional<User> cachedUser = getUserFromCache(username);

        return cachedUser.orElseGet(
                () -> getUserFromDataSourceIfCacheMiss(username)
        );
    }

    private Optional<User> getUserFromCache(String username) {
        return userRedisCacheRepository.selectUser(username);
    }

    private User getUserFromDataSourceIfCacheMiss(String username) {
        User user = userHardCodeRepository.getUser(username);
        userRedisCacheRepository.insertUser(user);
        return user;
    }
}
