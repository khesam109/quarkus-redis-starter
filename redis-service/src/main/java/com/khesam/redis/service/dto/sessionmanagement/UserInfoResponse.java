package com.khesam.redis.service.dto.sessionmanagement;

public record UserInfoResponse (
        String firstname,
        String lastname,
        String nationalCode
) {
}
