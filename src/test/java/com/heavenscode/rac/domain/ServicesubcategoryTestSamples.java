package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ServicesubcategoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Servicesubcategory getServicesubcategorySample1() {
        return new Servicesubcategory().id(1L).name("name1").description("description1").mainid(1).mainname("mainname1").lmu(1);
    }

    public static Servicesubcategory getServicesubcategorySample2() {
        return new Servicesubcategory().id(2L).name("name2").description("description2").mainid(2).mainname("mainname2").lmu(2);
    }

    public static Servicesubcategory getServicesubcategoryRandomSampleGenerator() {
        return new Servicesubcategory()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .mainid(intCount.incrementAndGet())
            .mainname(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
