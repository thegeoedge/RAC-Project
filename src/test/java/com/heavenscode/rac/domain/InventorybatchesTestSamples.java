package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InventorybatchesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Inventorybatches getInventorybatchesSample1() {
        return new Inventorybatches().id(1L).itemid(1).code("code1").notes("notes1").lmu(1).lineid(1);
    }

    public static Inventorybatches getInventorybatchesSample2() {
        return new Inventorybatches().id(2L).itemid(2).code("code2").notes("notes2").lmu(2).lineid(2);
    }

    public static Inventorybatches getInventorybatchesRandomSampleGenerator() {
        return new Inventorybatches()
            .id(longCount.incrementAndGet())
            .itemid(intCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .notes(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .lineid(intCount.incrementAndGet());
    }
}
