package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.redis.LockRedisRepository;
import com.khesam.redis.service.port.output.LockRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LockRepositoryAdapter implements LockRepository {

    private final LockRedisRepository lockRedisRepository;

    @Inject
    public LockRepositoryAdapter(
            LockRedisRepository lockRedisRepository
    ) {
        this.lockRedisRepository = lockRedisRepository;
    }

    @Override
    public boolean acquireLock(String signTrackingCode, String signerId) {
        return lockRedisRepository.setLock(
                signTrackingCode, signerId
        );
    }

    @Override
    public void releaseLock(String signTrackingCode) {
        lockRedisRepository.deleteLock(signTrackingCode);
    }
}
