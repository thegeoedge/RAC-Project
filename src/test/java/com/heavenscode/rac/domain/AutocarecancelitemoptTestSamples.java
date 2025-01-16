package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AutocarecancelitemoptTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Autocarecancelitemopt getAutocarecancelitemoptSample1() {
        return new Autocarecancelitemopt().id(1L).canceloption("canceloption1");
    }

    public static Autocarecancelitemopt getAutocarecancelitemoptSample2() {
        return new Autocarecancelitemopt().id(2L).canceloption("canceloption2");
    }

    public static Autocarecancelitemopt getAutocarecancelitemoptRandomSampleGenerator() {
        return new Autocarecancelitemopt().id(longCount.incrementAndGet()).canceloption(UUID.randomUUID().toString());
    }
}
