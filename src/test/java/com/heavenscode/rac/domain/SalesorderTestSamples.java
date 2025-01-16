package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SalesorderTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Salesorder getSalesorderSample1() {
        return new Salesorder()
            .id(1L)
            .code("code1")
            .quoteid(1)
            .salesrepid(1)
            .salesrepname("salesrepname1")
            .delieverfrom("delieverfrom1")
            .customerid(1)
            .customername("customername1")
            .customeraddress("customeraddress1")
            .deliveryaddress("deliveryaddress1")
            .message("message1")
            .refinvoiceid(1)
            .lmu(1)
            .advancepaymentby(1);
    }

    public static Salesorder getSalesorderSample2() {
        return new Salesorder()
            .id(2L)
            .code("code2")
            .quoteid(2)
            .salesrepid(2)
            .salesrepname("salesrepname2")
            .delieverfrom("delieverfrom2")
            .customerid(2)
            .customername("customername2")
            .customeraddress("customeraddress2")
            .deliveryaddress("deliveryaddress2")
            .message("message2")
            .refinvoiceid(2)
            .lmu(2)
            .advancepaymentby(2);
    }

    public static Salesorder getSalesorderRandomSampleGenerator() {
        return new Salesorder()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .quoteid(intCount.incrementAndGet())
            .salesrepid(intCount.incrementAndGet())
            .salesrepname(UUID.randomUUID().toString())
            .delieverfrom(UUID.randomUUID().toString())
            .customerid(intCount.incrementAndGet())
            .customername(UUID.randomUUID().toString())
            .customeraddress(UUID.randomUUID().toString())
            .deliveryaddress(UUID.randomUUID().toString())
            .message(UUID.randomUUID().toString())
            .refinvoiceid(intCount.incrementAndGet())
            .lmu(intCount.incrementAndGet())
            .advancepaymentby(intCount.incrementAndGet());
    }
}
