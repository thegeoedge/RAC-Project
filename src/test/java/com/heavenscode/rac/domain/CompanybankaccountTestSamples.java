package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CompanybankaccountTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Companybankaccount getCompanybankaccountSample1() {
        return new Companybankaccount()
            .id(1L)
            .companyid(1)
            .accountnumber("accountnumber1")
            .accountname("accountname1")
            .bankname("bankname1")
            .bankid(1)
            .branchname("branchname1")
            .branchid(1)
            .accountcode("accountcode1")
            .accountid(1)
            .lmu("lmu1")
            .accounttypeid(1);
    }

    public static Companybankaccount getCompanybankaccountSample2() {
        return new Companybankaccount()
            .id(2L)
            .companyid(2)
            .accountnumber("accountnumber2")
            .accountname("accountname2")
            .bankname("bankname2")
            .bankid(2)
            .branchname("branchname2")
            .branchid(2)
            .accountcode("accountcode2")
            .accountid(2)
            .lmu("lmu2")
            .accounttypeid(2);
    }

    public static Companybankaccount getCompanybankaccountRandomSampleGenerator() {
        return new Companybankaccount()
            .id(longCount.incrementAndGet())
            .companyid(intCount.incrementAndGet())
            .accountnumber(UUID.randomUUID().toString())
            .accountname(UUID.randomUUID().toString())
            .bankname(UUID.randomUUID().toString())
            .bankid(intCount.incrementAndGet())
            .branchname(UUID.randomUUID().toString())
            .branchid(intCount.incrementAndGet())
            .accountcode(UUID.randomUUID().toString())
            .accountid(intCount.incrementAndGet())
            .lmu(UUID.randomUUID().toString())
            .accounttypeid(intCount.incrementAndGet());
    }
}
