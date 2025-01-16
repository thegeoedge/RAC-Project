package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class VehiclecategoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Vehiclecategory getVehiclecategorySample1() {
        return new Vehiclecategory()
            .id(1L)
            .vehiclecategory("vehiclecategory1")
            .vehiclecategorydiscription("vehiclecategorydiscription1")
            .lmu(1);
    }

    public static Vehiclecategory getVehiclecategorySample2() {
        return new Vehiclecategory()
            .id(2L)
            .vehiclecategory("vehiclecategory2")
            .vehiclecategorydiscription("vehiclecategorydiscription2")
            .lmu(2);
    }

    public static Vehiclecategory getVehiclecategoryRandomSampleGenerator() {
        return new Vehiclecategory()
            .id(longCount.incrementAndGet())
            .vehiclecategory(UUID.randomUUID().toString())
            .vehiclecategorydiscription(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
