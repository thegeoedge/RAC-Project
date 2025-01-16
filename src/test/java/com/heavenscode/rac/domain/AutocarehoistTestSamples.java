package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutocarehoistTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autocarehoist getAutocarehoistSample1() {
        return new Autocarehoist()
            .id(1L)
            .hoistname("hoistname1")
            .hoistcode("hoistcode1")
            .hoisttypeid(1)
            .hoisttypename("hoisttypename1")
            .lmu(1);
    }

    public static Autocarehoist getAutocarehoistSample2() {
        return new Autocarehoist()
            .id(2L)
            .hoistname("hoistname2")
            .hoistcode("hoistcode2")
            .hoisttypeid(2)
            .hoisttypename("hoisttypename2")
            .lmu(2);
    }

    public static Autocarehoist getAutocarehoistRandomSampleGenerator() {
        return new Autocarehoist()
            .id(longCount.incrementAndGet())
            .hoistname(UUID.randomUUID().toString())
            .hoistcode(UUID.randomUUID().toString())
            .hoisttypeid(intCount.incrementAndGet())
            .hoisttypename(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
