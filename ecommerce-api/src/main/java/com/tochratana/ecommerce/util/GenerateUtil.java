package com.tochratana.ecommerce.util;

import java.util.concurrent.ThreadLocalRandom;

public final class GenerateUtil {

    private static final String PRODUCT_CODE_PREFIX = "LEARN-PRO-";

    private GenerateUtil() {
    }

    public static String randomProductCode() {
        int randomNumber = ThreadLocalRandom.current().nextInt(0, 100_000);
        return PRODUCT_CODE_PREFIX + String.format("%05d", randomNumber);
    }
}
