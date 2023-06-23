package com.wea4saken.rikmasters.cryptograph;

import java.util.Random;

public class RandomSerialNumberGenerator {

    private static final String CHARSET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int SN_LENGTH = 8;

    public static String generateSerialNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int charIndex;
        for (int i = 0; i < SN_LENGTH; i++) {
            charIndex = random.nextInt(CHARSET.length());
            sb.append(CHARSET.charAt(charIndex));
        }
        return sb.toString();
    }

}