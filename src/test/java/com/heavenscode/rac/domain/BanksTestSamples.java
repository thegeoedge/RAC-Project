package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BanksTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Banks getBanksSample1() {
        return new Banks().id(1L).code("code1").name("name1").description("description1").lmu(1);
    }

    public static Banks getBanksSample2() {
        return new Banks().id(2L).code("code2").name("name2").description("description2").lmu(2);
    }

    public static Banks getBanksRandomSampleGenerator() {
        return new Banks()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
