package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutocareservicemillagesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autocareservicemillages getAutocareservicemillagesSample1() {
        return new Autocareservicemillages().id(1L).millage(1);
    }

    public static Autocareservicemillages getAutocareservicemillagesSample2() {
        return new Autocareservicemillages().id(2L).millage(2);
    }

    public static Autocareservicemillages getAutocareservicemillagesRandomSampleGenerator() {
        return new Autocareservicemillages().id(longCount.incrementAndGet()).millage(intCount.incrementAndGet());
    }
}
