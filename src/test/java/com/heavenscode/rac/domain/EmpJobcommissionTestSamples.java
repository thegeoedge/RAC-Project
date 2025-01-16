package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class EmpJobcommissionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static EmpJobcommission getEmpJobcommissionSample1() {
        return new EmpJobcommission().id(1L).vehcatid(1L).autojobcatid(1L).lmu(1);
    }

    public static EmpJobcommission getEmpJobcommissionSample2() {
        return new EmpJobcommission().id(2L).vehcatid(2L).autojobcatid(2L).lmu(2);
    }

    public static EmpJobcommission getEmpJobcommissionRandomSampleGenerator() {
        return new EmpJobcommission()
            .id(longCount.incrementAndGet())
            .vehcatid(longCount.incrementAndGet())
            .autojobcatid(longCount.incrementAndGet())
            .lmu(intCount.incrementAndGet());
    }
}
