package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class SalesinvoiceTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Salesinvoice getSalesinvoiceSample1() {
        return new Salesinvoice()
            .id(1L)
            .code("code1")
            .quoteid(1)
            .orderid(1)
            .salesrepid(1)
            .salesrepname("salesrepname1")
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
            .autocarejobid(1)
            .vehicleno("vehicleno1")
            .nextmeter("nextmeter1")
            .currentmeter("currentmeter1")
            .remarks("remarks1")
            .dummybillid(1)
            .autocarecompanyid(1)
            .invcancelby(1)
            .paymenttype("paymenttype1")
            .discountcode("discountcode1");
    }

    public static Salesinvoice getSalesinvoiceSample2() {
        return new Salesinvoice()
            .id(2L)
            .code("code2")
            .quoteid(2)
            .orderid(2)
            .salesrepid(2)
            .salesrepname("salesrepname2")
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
            .autocarejobid(2)
            .vehicleno("vehicleno2")
            .nextmeter("nextmeter2")
            .currentmeter("currentmeter2")
            .remarks("remarks2")
            .dummybillid(2)
            .autocarecompanyid(2)
            .invcancelby(2)
            .paymenttype("paymenttype2")
            .discountcode("discountcode2");
    }

    public static Salesinvoice getSalesinvoiceRandomSampleGenerator() {
        return new Salesinvoice()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .quoteid(intCount.incrementAndGet())
            .orderid(intCount.incrementAndGet())
            .salesrepid(intCount.incrementAndGet())
            .salesrepname(UUID.randomUUID().toString())
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
            .autocarejobid(intCount.incrementAndGet())
            .vehicleno(UUID.randomUUID().toString())
            .nextmeter(UUID.randomUUID().toString())
            .currentmeter(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .dummybillid(intCount.incrementAndGet())
            .autocarecompanyid(intCount.incrementAndGet())
            .invcancelby(intCount.incrementAndGet())
            .paymenttype(UUID.randomUUID().toString())
            .discountcode(UUID.randomUUID().toString());
    }
}
