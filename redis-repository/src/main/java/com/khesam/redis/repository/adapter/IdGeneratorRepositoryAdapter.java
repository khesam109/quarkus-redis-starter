package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.redis.IdGeneratorRedisRepository;
import com.khesam.redis.service.port.output.IdGeneratorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IdGeneratorRepositoryAdapter implements IdGeneratorRepository {

    private final IdGeneratorRedisRepository idGeneratorRedisRepository;

    @Inject
    public IdGeneratorRepositoryAdapter(
            IdGeneratorRedisRepository idGeneratorRedisRepository
    ) {
        this.idGeneratorRedisRepository = idGeneratorRedisRepository;
    }

    @Override
    public long generateSignRequestId() {
        return idGeneratorRedisRepository.getGlobalSignRequestId();
    }
}
