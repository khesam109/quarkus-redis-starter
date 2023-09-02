package com.khesam.redis.repository.serializer;

import com.khesam.redis.repository.entity.CertificateEntity;
import com.khesam.redis.repository.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RedisCustomSerializer {

    public String serializeUserEntity(UserEntity userEntity) {
        return userEntity.getNationalCode() + ":" +
                userEntity.getUsername() + ":" +
                userEntity.getFirstname() + ":" +
                userEntity.getLastname();
    }

    public UserEntity deserializeUserEntity(String text) {
        String[] values = text.split(":");
        return UserEntity.Builder.builder()
                .nationalCode(values[0])
                .username(values[1])
                .firstname(values[2])
                .lastname(values[3])
                .build();
    }

    public String serializeCertificateEntity(CertificateEntity certificateEntity) {
        return certificateEntity.getId() + ":" +
                certificateEntity.getCsr() + ":" +
                certificateEntity.getStatus();
    }

    public CertificateEntity deserializeCertificateEntity(String text) {
        String[] values = text.split(":");
        return CertificateEntity.Builder.builder()
                .id(values[0])
                .csr(values[1])
                .status(values[2])
                .build();
    }
}
