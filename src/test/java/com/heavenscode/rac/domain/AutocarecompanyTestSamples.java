package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutocarecompanyTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autocarecompany getAutocarecompanySample1() {
        return new Autocarecompany()
            .id(1L)
            .name("name1")
            .address("address1")
            .servicephone("servicephone1")
            .sparepartphone("sparepartphone1")
            .bodypaint("bodypaint1")
            .generalphone("generalphone1")
            .fax("fax1")
            .email("email1")
            .description("description1")
            .lmu(1)
            .vatregnumber("vatregnumber1")
            .tinnumber("tinnumber1")
            .accountcode("accountcode1")
            .accountid(1);
    }

    public static Autocarecompany getAutocarecompanySample2() {
        return new Autocarecompany()
            .id(2L)
            .name("name2")
            .address("address2")
            .servicephone("servicephone2")
            .sparepartphone("sparepartphone2")
            .bodypaint("bodypaint2")
            .generalphone("generalphone2")
            .fax("fax2")
            .email("email2")
            .description("description2")
            .lmu(2)
            .vatregnumber("vatregnumber2")
            .tinnumber("tinnumber2")
            .accountcode("accountcode2")
            .accountid(2);
    }

    public static Autocarecompany getAutocarecompanyRandomSampleGenerator() {
        return new Autocarecompany()
            .id(longCount.incrementAndGet())
            .name(UUID.randomUUID().toString())
            .address(UUID.randomUUID().toString())
            .servicephone(UUID.randomUUID().toString())
            .sparepartphone(UUID.randomUUID().toString())
            .bodypaint(UUID.randomUUID().toString())
            .generalphone(UUID.randomUUID().toString())
            .fax(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .vatregnumber(UUID.randomUUID().toString())
            .tinnumber(UUID.randomUUID().toString())
            .accountcode(UUID.randomUUID().toString())
            .accountid(intCount.incrementAndGet());
    }
}
