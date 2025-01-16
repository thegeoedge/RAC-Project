package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HoisttypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Hoisttype getHoisttypeSample1() {
        return new Hoisttype().id(1L).hoisttype("hoisttype1").lmu(1);
    }

    public static Hoisttype getHoisttypeSample2() {
        return new Hoisttype().id(2L).hoisttype("hoisttype2").lmu(2);
    }

    public static Hoisttype getHoisttypeRandomSampleGenerator() {
        return new Hoisttype().id(longCount.incrementAndGet()).hoisttype(UUID.randomUUID().toString()).lmu(intCount.incrementAndGet());
    }
}
