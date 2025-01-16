package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class VehicletypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Vehicletype getVehicletypeSample1() {
        return new Vehicletype()
            .id(1L)
            .vehicletype("vehicletype1")
            .vehicletypediscription("vehicletypediscription1")
            .lmu(1)
            .catid(1)
            .catname("catname1");
    }

    public static Vehicletype getVehicletypeSample2() {
        return new Vehicletype()
            .id(2L)
            .vehicletype("vehicletype2")
            .vehicletypediscription("vehicletypediscription2")
            .lmu(2)
            .catid(2)
            .catname("catname2");
    }

    public static Vehicletype getVehicletypeRandomSampleGenerator() {
        return new Vehicletype()
            .id(longCount.incrementAndGet())
            .vehicletype(UUID.randomUUID().toString())
            .vehicletypediscription(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .catid(intCount.incrementAndGet())
            .catname(UUID.randomUUID().toString());
    }
}
