package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LastserviceinstructionsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Lastserviceinstructions getLastserviceinstructionsSample1() {
        return new Lastserviceinstructions().id(1L).jobid(1).instruction("instruction1").lmu(1).vehicleid(1).vehicleno("vehicleno1");
    }

    public static Lastserviceinstructions getLastserviceinstructionsSample2() {
        return new Lastserviceinstructions().id(2L).jobid(2).instruction("instruction2").lmu(2).vehicleid(2).vehicleno("vehicleno2");
    }

    public static Lastserviceinstructions getLastserviceinstructionsRandomSampleGenerator() {
        return new Lastserviceinstructions()
            .id(longCount.incrementAndGet())
            .jobid(intCount.incrementAndGet())
            .instruction(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .vehicleid(intCount.incrementAndGet())
            .vehicleno(UUID.randomUUID().toString());
    }
}
