package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutojobsinvoiceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autojobsinvoice getAutojobsinvoiceSample1() {
        return new Autojobsinvoice()
            .id(1L)
            .code("code1")
            .jobid(1)
            .quoteid(1)
            .orderid(1)
            .autojobsrepid(1)
            .autojobsrepname("autojobsrepname1")
            .delieverfrom("delieverfrom1")
            .customerid(1)
            .customername("customername1")
            .customeraddress("customeraddress1")
            .deliveryaddress("deliveryaddress1")
            .message("message1")
            .lmu(1)
            .locationid(1)
            .locationcode("locationcode1")
            .referencecode("referencecode1")
            .createdbyid(1)
            .createdbyname("createdbyname1")
            .autocarecompanyid(1);
    }

    public static Autojobsinvoice getAutojobsinvoiceSample2() {
        return new Autojobsinvoice()
            .id(2L)
            .code("code2")
            .jobid(2)
            .quoteid(2)
            .orderid(2)
            .autojobsrepid(2)
            .autojobsrepname("autojobsrepname2")
            .delieverfrom("delieverfrom2")
            .customerid(2)
            .customername("customername2")
            .customeraddress("customeraddress2")
            .deliveryaddress("deliveryaddress2")
            .message("message2")
            .lmu(2)
            .locationid(2)
            .locationcode("locationcode2")
            .referencecode("referencecode2")
            .createdbyid(2)
            .createdbyname("createdbyname2")
            .autocarecompanyid(2);
    }

    public static Autojobsinvoice getAutojobsinvoiceRandomSampleGenerator() {
        return new Autojobsinvoice()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .jobid(intCount.incrementAndGet())
            .quoteid(intCount.incrementAndGet())
            .orderid(intCount.incrementAndGet())
            .autojobsrepid(intCount.incrementAndGet())
            .autojobsrepname(UUID.randomUUID().toString())
            .delieverfrom(UUID.randomUUID().toString())
            .customerid(intCount.incrementAndGet())
            .customername(UUID.randomUUID().toString())
            .customeraddress(UUID.randomUUID().toString())
            .deliveryaddress(UUID.randomUUID().toString())
            .message(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .locationid(intCount.incrementAndGet())
            .locationcode(UUID.randomUUID().toString())
            .referencecode(UUID.randomUUID().toString())
            .createdbyid(intCount.incrementAndGet())
            .createdbyname(UUID.randomUUID().toString())
            .autocarecompanyid(intCount.incrementAndGet());
    }
}
