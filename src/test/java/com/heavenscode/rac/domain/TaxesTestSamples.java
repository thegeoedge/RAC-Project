package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TaxesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Taxes getTaxesSample1() {
        return new Taxes().id(1L).code("code1").description("description1").lmu(1);
    }

    public static Taxes getTaxesSample2() {
        return new Taxes().id(2L).code("code2").description("description2").lmu(2);
    }

    public static Taxes getTaxesRandomSampleGenerator() {
        return new Taxes()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
