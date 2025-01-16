package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutocareappointmentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autocareappointment getAutocareappointmentSample1() {
        return new Autocareappointment()
            .id(1L)
            .appointmenttype(1)
            .appointmentnumber(1)
            .vehiclenumber("vehiclenumber1")
            .conformedby(1)
            .lmu(1)
            .customerid(1)
            .contactnumber("contactnumber1")
            .customername("customername1")
            .hoistid(1)
            .missedappointmentcall("missedappointmentcall1")
            .customermobileid(1)
            .customermobilevehicleid(1)
            .vehicleid(1)
            .jobid(1);
    }

    public static Autocareappointment getAutocareappointmentSample2() {
        return new Autocareappointment()
            .id(2L)
            .appointmenttype(2)
            .appointmentnumber(2)
            .vehiclenumber("vehiclenumber2")
            .conformedby(2)
            .lmu(2)
            .customerid(2)
            .contactnumber("contactnumber2")
            .customername("customername2")
            .hoistid(2)
            .missedappointmentcall("missedappointmentcall2")
            .customermobileid(2)
            .customermobilevehicleid(2)
            .vehicleid(2)
            .jobid(2);
    }

    public static Autocareappointment getAutocareappointmentRandomSampleGenerator() {
        return new Autocareappointment()
            .id(longCount.incrementAndGet())
            .appointmenttype(intCount.incrementAndGet())
            .appointmentnumber(intCount.incrementAndGet())
            .vehiclenumber(UUID.randomUUID().toString())
            .conformedby(intCount.incrementAndGet())
            .lmu(intCount.incrementAndGet())
            .customerid(intCount.incrementAndGet())
            .contactnumber(UUID.randomUUID().toString())
            .customername(UUID.randomUUID().toString())
            .hoistid(intCount.incrementAndGet())
            .missedappointmentcall(UUID.randomUUID().toString())
            .customermobileid(intCount.incrementAndGet())
            .customermobilevehicleid(intCount.incrementAndGet())
            .vehicleid(intCount.incrementAndGet())
            .jobid(intCount.incrementAndGet());
    }
}
