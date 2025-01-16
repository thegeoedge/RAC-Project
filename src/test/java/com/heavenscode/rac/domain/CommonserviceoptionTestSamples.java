package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CommonserviceoptionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Commonserviceoption getCommonserviceoptionSample1() {
        return new Commonserviceoption().id(1L).mainid(1).code("code1").name("name1").description("description1").lmu(1);
    }

    public static Commonserviceoption getCommonserviceoptionSample2() {
        return new Commonserviceoption().id(2L).mainid(2).code("code2").name("name2").description("description2").lmu(2);
    }

    public static Commonserviceoption getCommonserviceoptionRandomSampleGenerator() {
        return new Commonserviceoption()
            .id(longCount.incrementAndGet())
            .mainid(intCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
