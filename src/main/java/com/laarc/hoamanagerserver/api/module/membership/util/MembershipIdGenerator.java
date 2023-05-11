package com.laarc.hoamanagerserver.api.module.membership.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MembershipIdGenerator {
    private static final int MEMBERSHIP_ID_LENGTH = 8;

    public String generateId() {
        String randomNumbers = generateRandomNumbers();
        String checkDigit = calculateCheckDigit(randomNumbers);
        return randomNumbers + checkDigit;
    }

    public boolean validateMembershipId(String id) {
        if (id == null || id.length() != MEMBERSHIP_ID_LENGTH) {
            return false;
        }
        String randomNumbers = id.substring(0, MEMBERSHIP_ID_LENGTH - 1);
        String checkDigit = id.substring(MEMBERSHIP_ID_LENGTH - 1);
        return checkDigit.equals(calculateCheckDigit(randomNumbers));
    }

    private String generateRandomNumbers() {
        StringBuilder sb = new StringBuilder(7);
        Random random = new Random();
        for (int i = 0; i < 7; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    private String calculateCheckDigit(String numbers) {
        int sum = 0;
        int multiplier = 3;
        for (int i = numbers.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(numbers.substring(i, i + 1));
            sum += digit * multiplier;
            multiplier = (multiplier == 3) ? 1 : 3;
        }
        int remainder = sum % 10;
        int checkDigit = (remainder == 0) ? 0 : (10 - remainder);
        return String.valueOf(checkDigit);
    }
}
