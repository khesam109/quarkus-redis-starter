package com.khesam.redis.service;

import com.khesam.redis.service.exception.IllegalConcurrentAccessException;
import com.khesam.redis.service.port.input.SignService;
import com.khesam.redis.service.port.output.LockRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SignServiceImpl implements SignService {

    private final LockRepository lockRepository;

    @Inject
    public SignServiceImpl(
            LockRepository lockRepository
    ) {
        this.lockRepository = lockRepository;
    }

    @Override
    public void sign(String singerId, String signTrackingCode) {
        acquireLock(singerId, signTrackingCode);
        longRunningOperationSimulation();
        releaseLock(signTrackingCode);
    }

    private void acquireLock(String singerId, String signTrackingCode) {
        boolean result = lockRepository.acquireLock(signTrackingCode, singerId);
        if (!result) {
            throw new IllegalConcurrentAccessException("The sign request is processing currently");
        }
    }

    private void releaseLock(String signTrackingCode) {
        lockRepository.releaseLock(signTrackingCode);
    }

    private void longRunningOperationSimulation() {
        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
