package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutocaretimetableTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autocaretimetable getAutocaretimetableSample1() {
        return new Autocaretimetable().id(1L).hoisttypeid(1).hoisttypename("hoisttypename1");
    }

    public static Autocaretimetable getAutocaretimetableSample2() {
        return new Autocaretimetable().id(2L).hoisttypeid(2).hoisttypename("hoisttypename2");
    }

    public static Autocaretimetable getAutocaretimetableRandomSampleGenerator() {
        return new Autocaretimetable()
            .id(longCount.incrementAndGet())
            .hoisttypeid(intCount.incrementAndGet())
            .hoisttypename(UUID.randomUUID().toString());
    }
}
