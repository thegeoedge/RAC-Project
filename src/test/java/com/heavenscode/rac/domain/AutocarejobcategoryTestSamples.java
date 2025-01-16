package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutocarejobcategoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autocarejobcategory getAutocarejobcategorySample1() {
        return new Autocarejobcategory().id(1L).code("code1").name("name1").description("description1").lmu(1).displayorder(1);
    }

    public static Autocarejobcategory getAutocarejobcategorySample2() {
        return new Autocarejobcategory().id(2L).code("code2").name("name2").description("description2").lmu(2).displayorder(2);
    }

    public static Autocarejobcategory getAutocarejobcategoryRandomSampleGenerator() {
        return new Autocarejobcategory()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .displayorder(intCount.incrementAndGet());
    }
}
