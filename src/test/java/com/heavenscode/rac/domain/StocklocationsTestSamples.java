package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class StocklocationsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Stocklocations getStocklocationsSample1() {
        return new Stocklocations().id(1L).locationname("locationname1").locationcode("locationcode1").description("description1");
    }

    public static Stocklocations getStocklocationsSample2() {
        return new Stocklocations().id(2L).locationname("locationname2").locationcode("locationcode2").description("description2");
    }

    public static Stocklocations getStocklocationsRandomSampleGenerator() {
        return new Stocklocations()
            .id(longCount.incrementAndGet())
            .locationname(UUID.randomUUID().toString())
            .locationcode(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
