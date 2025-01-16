package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutojobempallocationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autojobempallocation getAutojobempallocationSample1() {
        return new Autojobempallocation().id(1L).jobid(1).categoryid(1).empposition(1).empid(1).empname("empname1").lmu(1);
    }

    public static Autojobempallocation getAutojobempallocationSample2() {
        return new Autojobempallocation().id(2L).jobid(2).categoryid(2).empposition(2).empid(2).empname("empname2").lmu(2);
    }

    public static Autojobempallocation getAutojobempallocationRandomSampleGenerator() {
        return new Autojobempallocation()
            .id(longCount.incrementAndGet())
            .jobid(intCount.incrementAndGet())
            .categoryid(intCount.incrementAndGet())
            .empposition(intCount.incrementAndGet())
            .empid(intCount.incrementAndGet())
            .empname(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
