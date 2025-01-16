package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class WorkshopworklistTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Workshopworklist getWorkshopworklistSample1() {
        return new Workshopworklist().id(1L).workshopwork("workshopwork1").workshopworkdescription("workshopworkdescription1").lmu(1);
    }

    public static Workshopworklist getWorkshopworklistSample2() {
        return new Workshopworklist().id(2L).workshopwork("workshopwork2").workshopworkdescription("workshopworkdescription2").lmu(2);
    }

    public static Workshopworklist getWorkshopworklistRandomSampleGenerator() {
        return new Workshopworklist()
            .id(longCount.incrementAndGet())
            .workshopwork(UUID.randomUUID().toString())
            .workshopworkdescription(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
