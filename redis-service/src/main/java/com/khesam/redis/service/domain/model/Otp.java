package com.khesam.redis.service.domain.model;

import com.khesam.redis.service.domain.valueobject.OtpId;
import com.khesam.redis.service.exception.OtpException;

import java.util.UUID;

public class Otp extends BaseModel<OtpId> {

    private static final int MAX_WRONG_ATTEMPT = 3;

    private int wrongAttempts;
    private String password;
    private OtpStatus status;

    public void createOtp(String password) {
        setId(new OtpId(UUID.randomUUID()));
        this.wrongAttempts = 0;
        this.password = password;
        this.status = OtpStatus.PENDING;
    }

    public boolean verifyOtp(String password) {
        if (this.password.equals(password)) {
            this.status = OtpStatus.VERIFIED;
            return true;
        } else if (wrongAttempts < MAX_WRONG_ATTEMPT) {
            this.wrongAttempts++;
            return false;
        }
        this.status = OtpStatus.FUCKED_UP;
        return false;
    }

    public void validateOtpStatus() {
        if (this.status != OtpStatus.PENDING) {
            throw new OtpException("Otp with " + getId().getValue().toString() + " is " + status.toString());
        }
    }

    public int getWrongAttempts() {
        return wrongAttempts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OtpStatus getStatus() {
        return status;
    }
}
