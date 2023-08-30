package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.redis.SuspensionRedisRepository;
import com.khesam.redis.service.port.output.SuspensionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SuspensionRepositoryAdapter implements SuspensionRepository {

    private final SuspensionRedisRepository suspensionRedisRepository;

    @Inject
    public SuspensionRepositoryAdapter(
            SuspensionRedisRepository suspensionRedisRepository
    ) {
        this.suspensionRedisRepository = suspensionRedisRepository;
    }

    @Override
    public boolean isSuspended(String username) {
        return suspensionRedisRepository.isSuspended(username);
    }

    @Override
    public long addWrongAttempt(String username) {
        return suspensionRedisRepository.addWrongAttempt(username);
    }

    @Override
    public void clearWrongAttempt(String username) {
        suspensionRedisRepository.clearWrongAttempt(username);
    }

    @Override
    public void suspendUser(String username) {
        suspensionRedisRepository.suspendUser(username);
    }
}
