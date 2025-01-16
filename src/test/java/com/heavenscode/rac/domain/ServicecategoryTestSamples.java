package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ServicecategoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Servicecategory getServicecategorySample1() {
        return new Servicecategory().id(1L).name("name1").description("description1").lmu(1).sortorder(1);
    }

    public static Servicecategory getServicecategorySample2() {
        return new Servicecategory().id(2L).name("name2").description("description2").lmu(2).sortorder(2);
    }

    public static Servicecategory getServicecategoryRandomSampleGenerator() {
        return new Servicecategory()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .sortorder(intCount.incrementAndGet());
    }
}
