package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class LocationbasedstockTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Locationbasedstock getLocationbasedstockSample1() {
        return new Locationbasedstock().id(1L).itemid(1).code("code1").name("name1").locationid(1).locationcode("locationcode1").lmu(1);
    }

    public static Locationbasedstock getLocationbasedstockSample2() {
        return new Locationbasedstock().id(2L).itemid(2).code("code2").name("name2").locationid(2).locationcode("locationcode2").lmu(2);
    }

    public static Locationbasedstock getLocationbasedstockRandomSampleGenerator() {
        return new Locationbasedstock()
            .id(longCount.incrementAndGet())
            .itemid(intCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .locationid(intCount.incrementAndGet())
            .locationcode(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
