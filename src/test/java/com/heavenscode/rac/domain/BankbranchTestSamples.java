package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BankbranchTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Bankbranch getBankbranchSample1() {
        return new Bankbranch().id(1L).bankcode("bankcode1").branchcode("branchcode1").branchname("branchname1");
    }

    public static Bankbranch getBankbranchSample2() {
        return new Bankbranch().id(2L).bankcode("bankcode2").branchcode("branchcode2").branchname("branchname2");
    }

    public static Bankbranch getBankbranchRandomSampleGenerator() {
        return new Bankbranch()
            .id(longCount.incrementAndGet())
            .bankcode(UUID.randomUUID().toString())
            .branchcode(UUID.randomUUID().toString())
            .branchname(UUID.randomUUID().toString());
    }
}
