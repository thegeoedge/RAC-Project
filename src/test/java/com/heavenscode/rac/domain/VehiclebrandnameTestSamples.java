package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class VehiclebrandnameTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Vehiclebrandname getVehiclebrandnameSample1() {
        return new Vehiclebrandname().id(1L).brandname("brandname1").description("description1").lmu(1).companyid(1);
    }

    public static Vehiclebrandname getVehiclebrandnameSample2() {
        return new Vehiclebrandname().id(2L).brandname("brandname2").description("description2").lmu(2).companyid(2);
    }

    public static Vehiclebrandname getVehiclebrandnameRandomSampleGenerator() {
        return new Vehiclebrandname()
            .id(longCount.incrementAndGet())
            .brandname(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .companyid(intCount.incrementAndGet());
    }
}
