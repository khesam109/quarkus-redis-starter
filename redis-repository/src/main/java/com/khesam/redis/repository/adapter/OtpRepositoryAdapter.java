package com.khesam.redis.repository.adapter;

import com.khesam.redis.repository.redis.OtpRedisRepository;
import com.khesam.redis.service.domain.model.otp.Otp;
import com.khesam.redis.service.domain.valueobject.OtpId;
import com.khesam.redis.service.exception.OtpException;
import com.khesam.redis.service.port.output.OtpRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class OtpRepositoryAdapter implements OtpRepository {

    private final OtpRedisRepository otpRedisRepository;

    @Inject
    public OtpRepositoryAdapter(OtpRedisRepository otpRedisRepository) {
        this.otpRedisRepository = otpRedisRepository;
    }

    @Override
    public void saveOtp(Otp otp) {
        otpRedisRepository.insertOtp(otp.getId().getValue().toString(), otp.getPassword());
    }

    @Override
    public Otp getOtp(OtpId otpId) {
        Optional<String> password = otpRedisRepository.selectOtp(
                otpId.getValue().toString()
        );
        if (password.isEmpty()) {
            throw new OtpException("Invalid Otp Id");
        }

        Otp otp = new Otp();
        otp.setId(otpId);
        otp.setPassword(password.get());
        return otp;
    }
}
