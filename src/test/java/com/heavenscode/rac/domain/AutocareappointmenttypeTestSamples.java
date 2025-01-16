package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AutocareappointmenttypeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Autocareappointmenttype getAutocareappointmenttypeSample1() {
        return new Autocareappointmenttype().id(1L).typename("typename1");
    }

    public static Autocareappointmenttype getAutocareappointmenttypeSample2() {
        return new Autocareappointmenttype().id(2L).typename("typename2");
    }

    public static Autocareappointmenttype getAutocareappointmenttypeRandomSampleGenerator() {
        return new Autocareappointmenttype().id(longCount.incrementAndGet()).typename(UUID.randomUUID().toString());
    }
}
