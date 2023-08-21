package com.khesam.redis.service.common;

import jakarta.enterprise.context.ApplicationScoped;

import java.security.SecureRandom;
import java.util.Random;

@ApplicationScoped
public class RandomUtils {

    private static final char[] DIGITS = new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final Random RANDOM = new SecureRandom();

    public String generateNumericRandom(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Invalid size for generating random number");
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < size; ++i) {
            stringBuilder.append(DIGITS[RANDOM.nextInt(DIGITS.length)]);
        }

        return stringBuilder.toString();
    }
}
