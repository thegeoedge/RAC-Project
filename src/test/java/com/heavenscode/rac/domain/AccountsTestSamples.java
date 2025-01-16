package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AccountsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Accounts getAccountsSample1() {
        return new Accounts()
            .id(1L)
            .code("code1")
            .name("name1")
            .description("description1")
            .type(1)
            .parent(1)
            .lmu(1)
            .accountlevel(1)
            .accountsnumberingsystem(1L)
            .subparentid(1)
            .debitorcredit("debitorcredit1")
            .reporttype(1);
    }

    public static Accounts getAccountsSample2() {
        return new Accounts()
            .id(2L)
            .code("code2")
            .name("name2")
            .description("description2")
            .type(2)
            .parent(2)
            .lmu(2)
            .accountlevel(2)
            .accountsnumberingsystem(2L)
            .subparentid(2)
            .debitorcredit("debitorcredit2")
            .reporttype(2);
    }

    public static Accounts getAccountsRandomSampleGenerator() {
        return new Accounts()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .type(intCount.incrementAndGet())
            .parent(intCount.incrementAndGet())
            .lmu(intCount.incrementAndGet())
            .accountlevel(intCount.incrementAndGet())
            .accountsnumberingsystem(longCount.incrementAndGet())
            .subparentid(intCount.incrementAndGet())
            .debitorcredit(UUID.randomUUID().toString())
            .reporttype(intCount.incrementAndGet());
    }
}
