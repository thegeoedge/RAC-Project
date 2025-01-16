package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BillingserviceoptionvaluesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Billingserviceoptionvalues getBillingserviceoptionvaluesSample1() {
        return new Billingserviceoptionvalues().id(1L).vehicletypeid(1L).billingserviceoptionid(1L).lmu(1);
    }

    public static Billingserviceoptionvalues getBillingserviceoptionvaluesSample2() {
        return new Billingserviceoptionvalues().id(2L).vehicletypeid(2L).billingserviceoptionid(2L).lmu(2);
    }

    public static Billingserviceoptionvalues getBillingserviceoptionvaluesRandomSampleGenerator() {
        return new Billingserviceoptionvalues()
            .id(longCount.incrementAndGet())
            .vehicletypeid(longCount.incrementAndGet())
            .billingserviceoptionid(longCount.incrementAndGet())
            .lmu(intCount.incrementAndGet());
    }
}
