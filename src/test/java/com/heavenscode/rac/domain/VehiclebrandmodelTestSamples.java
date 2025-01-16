package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class VehiclebrandmodelTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Vehiclebrandmodel getVehiclebrandmodelSample1() {
        return new Vehiclebrandmodel().id(1L).brandid(1).brandname("brandname1").model("model1").lmu(1);
    }

    public static Vehiclebrandmodel getVehiclebrandmodelSample2() {
        return new Vehiclebrandmodel().id(2L).brandid(2).brandname("brandname2").model("model2").lmu(2);
    }

    public static Vehiclebrandmodel getVehiclebrandmodelRandomSampleGenerator() {
        return new Vehiclebrandmodel()
            .id(longCount.incrementAndGet())
            .brandid(intCount.incrementAndGet())
            .brandname(UUID.randomUUID().toString())
            .model(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
