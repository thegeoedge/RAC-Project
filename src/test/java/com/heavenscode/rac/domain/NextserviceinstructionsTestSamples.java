package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class NextserviceinstructionsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Nextserviceinstructions getNextserviceinstructionsSample1() {
        return new Nextserviceinstructions().id(1L).jobid(1).instruction("instruction1").lmu(1).vehicleid(1).vehicleno("vehicleno1");
    }

    public static Nextserviceinstructions getNextserviceinstructionsSample2() {
        return new Nextserviceinstructions().id(2L).jobid(2).instruction("instruction2").lmu(2).vehicleid(2).vehicleno("vehicleno2");
    }

    public static Nextserviceinstructions getNextserviceinstructionsRandomSampleGenerator() {
        return new Nextserviceinstructions()
            .id(longCount.incrementAndGet())
            .jobid(intCount.incrementAndGet())
            .instruction(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .vehicleid(intCount.incrementAndGet())
            .vehicleno(UUID.randomUUID().toString());
    }
}
