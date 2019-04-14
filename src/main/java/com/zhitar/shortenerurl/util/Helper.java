package com.zhitar.shortenerurl.util;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class Helper {

    private static final Random GENERATOR = new SecureRandom();
    private static final String PREFIX = "http://localhost:8080/";

    private Helper() {
    }

    public static String generateRandomString() {
        return PREFIX + new BigInteger(32, GENERATOR).toString(36);
    }
}
