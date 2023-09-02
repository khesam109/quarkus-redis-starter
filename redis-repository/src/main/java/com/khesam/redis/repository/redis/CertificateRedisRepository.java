package com.khesam.redis.repository.redis;

import com.khesam.redis.repository.mapper.CertificateMapper;
import com.khesam.redis.repository.serializer.RedisCustomSerializer;
import com.khesam.redis.service.domain.model.certificate.Certificate;
import com.khesam.redis.service.domain.valueobject.CertificateId;
import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CertificateRedisRepository {

    private static final String CERTIFICATE_KEY = "certificate:%s";

    private final ValueCommands<String, String> commands;
    private final RedisCustomSerializer redisCustomSerializer;
    private final CertificateMapper certificateMapper;


    @Inject
    public CertificateRedisRepository(
            RedisDataSource dataSource,
            RedisCustomSerializer redisCustomSerializer,
            CertificateMapper certificateMapper
    ) {
        commands = dataSource.value(String.class);
        this.redisCustomSerializer = redisCustomSerializer;
        this.certificateMapper = certificateMapper;
    }

    public void insertCertificate(Certificate certificate) {
        commands.set(
                buildKey(certificate.getId().getValue().toString()),
                redisCustomSerializer.serializeCertificateEntity(
                        certificateMapper.toCertificateEntity(
                                certificate
                        )
                )
        );
    }

    public Certificate selectCertificate(CertificateId certificateId) {
        return certificateMapper.fromCertificateEntity(
                redisCustomSerializer.deserializeCertificateEntity(
                        commands.get(
                                buildKey(certificateId.getValue().toString())
                        )
                )
        );
    }

    public void updateCertificate(Certificate certificate) {
        commands.set(
                buildKey(certificate.getId().getValue().toString()),
                redisCustomSerializer.serializeCertificateEntity(
                        certificateMapper.toCertificateEntity(
                                certificate
                        )
                )
        );
    }

    private String buildKey(String id) {
        return String.format(
                CERTIFICATE_KEY, id
        );
    }
}
