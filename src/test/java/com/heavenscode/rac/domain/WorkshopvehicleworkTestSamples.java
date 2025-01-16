package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class WorkshopvehicleworkTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Workshopvehiclework getWorkshopvehicleworkSample1() {
        return new Workshopvehiclework()
            .id(1L)
            .jobid(1)
            .vehicleid(1)
            .customerid(1)
            .customername("customername1")
            .contactno("contactno1")
            .vehicleno("vehicleno1")
            .vehiclebrand("vehiclebrand1")
            .vehiclemodel("vehiclemodel1")
            .mileage("mileage1")
            .remarks("remarks1")
            .lmu(1);
    }

    public static Workshopvehiclework getWorkshopvehicleworkSample2() {
        return new Workshopvehiclework()
            .id(2L)
            .jobid(2)
            .vehicleid(2)
            .customerid(2)
            .customername("customername2")
            .contactno("contactno2")
            .vehicleno("vehicleno2")
            .vehiclebrand("vehiclebrand2")
            .vehiclemodel("vehiclemodel2")
            .mileage("mileage2")
            .remarks("remarks2")
            .lmu(2);
    }

    public static Workshopvehiclework getWorkshopvehicleworkRandomSampleGenerator() {
        return new Workshopvehiclework()
            .id(longCount.incrementAndGet())
            .jobid(intCount.incrementAndGet())
            .vehicleid(intCount.incrementAndGet())
            .customerid(intCount.incrementAndGet())
            .customername(UUID.randomUUID().toString())
            .contactno(UUID.randomUUID().toString())
            .vehicleno(UUID.randomUUID().toString())
            .vehiclebrand(UUID.randomUUID().toString())
            .vehiclemodel(UUID.randomUUID().toString())
            .mileage(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet());
    }
}
