package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PaymenttermTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Paymentterm getPaymenttermSample1() {
        return new Paymentterm().id(1L).paymenttype("paymenttype1");
    }

    public static Paymentterm getPaymenttermSample2() {
        return new Paymentterm().id(2L).paymenttype("paymenttype2");
    }

    public static Paymentterm getPaymenttermRandomSampleGenerator() {
        return new Paymentterm().id(longCount.incrementAndGet()).paymenttype(UUID.randomUUID().toString());
    }
}
