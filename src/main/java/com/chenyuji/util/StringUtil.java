package com.chenyuji.util;

import java.util.Random;

public class StringUtil {
    private static final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static final char[] RANDOM_CHARS = "abcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
    private static final Random rnd_ = new Random();
    public static String randStringWithoutSms(int length) {
        String s = randString(length);
        if (s.toLowerCase().indexOf("sms") >= 0) {
            return randStringWithoutSms(length);
        }
        return s;
    }
    public static String randString(int length) {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_CHARS[rnd_.nextInt(RANDOM_CHARS.length)]);
        }

        return sb.toString();
    }
    public static boolean isEmpty(String val) {
        return val == null || val.isEmpty();
    }
}
