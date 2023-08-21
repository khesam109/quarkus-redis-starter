package com.khesam.redis.service;

import com.khesam.redis.service.common.RandomUtils;
import com.khesam.redis.service.domain.model.Otp;
import com.khesam.redis.service.domain.model.OtpUsage;
import com.khesam.redis.service.domain.valueobject.OtpId;
import com.khesam.redis.service.dto.SendOtpCommand;
import com.khesam.redis.service.dto.SendOtpResponse;
import com.khesam.redis.service.port.input.OtpService;
import com.khesam.redis.service.port.output.OtpRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.ZonedDateTime;
import java.util.UUID;

@ApplicationScoped
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final RandomUtils randomUtils;

    @Inject
    public OtpServiceImpl(
            OtpRepository otpRepository,
            RandomUtils randomUtils
    ) {
        this.otpRepository = otpRepository;
        this.randomUtils = randomUtils;
    }

    @Override
    public SendOtpResponse createOtp(SendOtpCommand sendOtpCommand) {
        Otp otp = new Otp();
        otp.createOtp(randomUtils.generateNumericRandom(4));

        otpRepository.saveOtp(otp);

        return new SendOtpResponse(
                otp.getId().getValue(),
                ZonedDateTime.now()
        );
    }

    @Override
    public boolean verifyOtp(String trackingCode, String password) {
        Otp otp = otpRepository.getOtp(new OtpId(UUID.fromString(trackingCode)));
        return otp.verifyOtp(password);
    }
}
