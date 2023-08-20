package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.redis.OtpRedisRepository;
import com.khesam.redis.service.domain.model.Otp;
import com.khesam.redis.service.domain.valueobject.OtpId;
import com.khesam.redis.service.port.output.OtpRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class OtpRepositoryImpl implements OtpRepository {

    private final OtpRedisRepository otpRedisRepository;

    @Inject
    public OtpRepositoryImpl(OtpRedisRepository otpRedisRepository) {
        this.otpRedisRepository = otpRedisRepository;
    }

    @Override
    public void saveOtp(Otp otp) {
        otpRedisRepository.insertOtp(otp.getMobileNumber(), otp.getPassword());
    }

    @Override
    public Optional<Otp> getOtp(OtpId otpId) {
        return Optional.empty();
    }
}
