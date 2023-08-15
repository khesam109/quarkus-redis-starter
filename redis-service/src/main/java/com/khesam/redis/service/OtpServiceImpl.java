package com.khesam.redis.service;

import com.khesam.redis.service.domain.model.Otp;
import com.khesam.redis.service.domain.model.OtpUsage;
import com.khesam.redis.service.dto.SendOtpCommand;
import com.khesam.redis.service.dto.SendOtpResponse;
import com.khesam.redis.service.port.input.OtpService;
import com.khesam.redis.service.port.output.OtpRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.ZonedDateTime;

@ApplicationScoped
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;

    @Inject
    public OtpServiceImpl(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }

    @Override
    public SendOtpResponse createOtp(SendOtpCommand sendOtpCommand) {
        Otp otp = new Otp(OtpUsage.getUsage(sendOtpCommand.usage()), sendOtpCommand.mobileNumber());
        otp.createOtp();

        otpRepository.saveOtp(otp);

        return new SendOtpResponse(
                otp.getId().getValue(),
                ZonedDateTime.now()
        );
    }
}
