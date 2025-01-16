package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class EmpSectiontblTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static EmpSectiontbl getEmpSectiontblSample1() {
        return new EmpSectiontbl().id(1L).empid(1L).sectionid(1).lmu(1);
    }

    public static EmpSectiontbl getEmpSectiontblSample2() {
        return new EmpSectiontbl().id(2L).empid(2L).sectionid(2).lmu(2);
    }

    public static EmpSectiontbl getEmpSectiontblRandomSampleGenerator() {
        return new EmpSectiontbl()
            .id(longCount.incrementAndGet())
            .empid(longCount.incrementAndGet())
            .sectionid(intCount.incrementAndGet())
            .lmu(intCount.incrementAndGet());
    }
}
