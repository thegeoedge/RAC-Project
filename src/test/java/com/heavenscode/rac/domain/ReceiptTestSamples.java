package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ReceiptTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Receipt getReceiptSample1() {
        return new Receipt()
            .id(1L)
            .code("code1")
            .customername("customername1")
            .customeraddress("customeraddress1")
            .totalamountinword("totalamountinword1")
            .comments("comments1")
            .lmu(1)
            .termid(1)
            .term("term1")
            .checkno("checkno1")
            .bank("bank1")
            .customerid(1)
            .createdby(1);
    }

    public static Receipt getReceiptSample2() {
        return new Receipt()
            .id(2L)
            .code("code2")
            .customername("customername2")
            .customeraddress("customeraddress2")
            .totalamountinword("totalamountinword2")
            .comments("comments2")
            .lmu(2)
            .termid(2)
            .term("term2")
            .checkno("checkno2")
            .bank("bank2")
            .customerid(2)
            .createdby(2);
    }

    public static Receipt getReceiptRandomSampleGenerator() {
        return new Receipt()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .customername(UUID.randomUUID().toString())
            .customeraddress(UUID.randomUUID().toString())
            .totalamountinword(UUID.randomUUID().toString())
            .comments(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .termid(intCount.incrementAndGet())
            .term(UUID.randomUUID().toString())
            .checkno(UUID.randomUUID().toString())
            .bank(UUID.randomUUID().toString())
            .customerid(intCount.incrementAndGet())
            .createdby(intCount.incrementAndGet());
    }
}
