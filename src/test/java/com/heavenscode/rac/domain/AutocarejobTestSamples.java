package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutocarejobTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autocarejob getAutocarejobSample1() {
        return new Autocarejob()
            .id(1L)
            .jobnumber(1)
            .vehicleid(1)
            .vehiclenumber("vehiclenumber1")
            .vehicletypeid(1)
            .jobtypeid(1)
            .jobtypename("jobtypename1")
            .jobopenby(1)
            .lmu(1)
            .specialrquirments("specialrquirments1")
            .specialinstructions("specialinstructions1")
            .remarks("remarks1")
            .nextserviceinstructions("nextserviceinstructions1")
            .lastserviceinstructions("lastserviceinstructions1")
            .feedbackstatusid(1)
            .customername("customername1")
            .customertel("customertel1")
            .customerid(1)
            .freeservicenumber("freeservicenumber1")
            .companyid(1)
            .nextgearoilmilage("nextgearoilmilage1")
            .imagefolder("imagefolder1")
            .frontimage("frontimage1")
            .leftimage("leftimage1")
            .rightimage("rightimage1")
            .backimage("backimage1")
            .dashboardimage("dashboardimage1");
    }

    public static Autocarejob getAutocarejobSample2() {
        return new Autocarejob()
            .id(2L)
            .jobnumber(2)
            .vehicleid(2)
            .vehiclenumber("vehiclenumber2")
            .vehicletypeid(2)
            .jobtypeid(2)
            .jobtypename("jobtypename2")
            .jobopenby(2)
            .lmu(2)
            .specialrquirments("specialrquirments2")
            .specialinstructions("specialinstructions2")
            .remarks("remarks2")
            .nextserviceinstructions("nextserviceinstructions2")
            .lastserviceinstructions("lastserviceinstructions2")
            .feedbackstatusid(2)
            .customername("customername2")
            .customertel("customertel2")
            .customerid(2)
            .freeservicenumber("freeservicenumber2")
            .companyid(2)
            .nextgearoilmilage("nextgearoilmilage2")
            .imagefolder("imagefolder2")
            .frontimage("frontimage2")
            .leftimage("leftimage2")
            .rightimage("rightimage2")
            .backimage("backimage2")
            .dashboardimage("dashboardimage2");
    }

    public static Autocarejob getAutocarejobRandomSampleGenerator() {
        return new Autocarejob()
            .id(longCount.incrementAndGet())
            .jobnumber(intCount.incrementAndGet())
            .vehicleid(intCount.incrementAndGet())
            .vehiclenumber(UUID.randomUUID().toString())
            .vehicletypeid(intCount.incrementAndGet())
            .jobtypeid(intCount.incrementAndGet())
            .jobtypename(UUID.randomUUID().toString())
            .jobopenby(intCount.incrementAndGet())
            .lmu(intCount.incrementAndGet())
            .specialrquirments(UUID.randomUUID().toString())
            .specialinstructions(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .nextserviceinstructions(UUID.randomUUID().toString())
            .lastserviceinstructions(UUID.randomUUID().toString())
            .feedbackstatusid(intCount.incrementAndGet())
            .customername(UUID.randomUUID().toString())
            .customertel(UUID.randomUUID().toString())
            .customerid(intCount.incrementAndGet())
            .freeservicenumber(UUID.randomUUID().toString())
            .companyid(intCount.incrementAndGet())
            .nextgearoilmilage(UUID.randomUUID().toString())
            .imagefolder(UUID.randomUUID().toString())
            .frontimage(UUID.randomUUID().toString())
            .leftimage(UUID.randomUUID().toString())
            .rightimage(UUID.randomUUID().toString())
            .backimage(UUID.randomUUID().toString())
            .dashboardimage(UUID.randomUUID().toString());
    }
}
