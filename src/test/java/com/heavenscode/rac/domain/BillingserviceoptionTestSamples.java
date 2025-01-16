package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BillingserviceoptionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Billingserviceoption getBillingserviceoptionSample1() {
        return new Billingserviceoption().id(1L).servicename("servicename1").servicediscription("servicediscription1").lmu(1).orderby(1);
    }

    public static Billingserviceoption getBillingserviceoptionSample2() {
        return new Billingserviceoption().id(2L).servicename("servicename2").servicediscription("servicediscription2").lmu(2).orderby(2);
    }

    public static Billingserviceoption getBillingserviceoptionRandomSampleGenerator() {
        return new Billingserviceoption()
            .id(longCount.incrementAndGet())
            .servicename(UUID.randomUUID().toString())
            .servicediscription(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .orderby(intCount.incrementAndGet());
    }
}
