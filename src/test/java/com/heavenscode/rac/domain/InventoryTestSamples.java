package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InventoryTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Inventory getInventorySample1() {
        return new Inventory()
            .id(1L)
            .code("code1")
            .partnumber("partnumber1")
            .name("name1")
            .description("description1")
            .type(1)
            .classification1("classification11")
            .classification2("classification21")
            .classification3("classification31")
            .classification4("classification41")
            .classification5("classification51")
            .unitofmeasurement("unitofmeasurement1")
            .decimalplaces(1)
            .assemblyunitof(1)
            .lmu(1)
            .itemspecfilepath("itemspecfilepath1")
            .itemimagepath("itemimagepath1")
            .accountcode("accountcode1")
            .accountid(1)
            .casepackqty(1)
            .defaultstocklocationid(1)
            .rackno("rackno1")
            .commissionempid(1)
            .checktypeid(1)
            .checktype("checktype1");
    }

    public static Inventory getInventorySample2() {
        return new Inventory()
            .id(2L)
            .code("code2")
            .partnumber("partnumber2")
            .name("name2")
            .description("description2")
            .type(2)
            .classification1("classification12")
            .classification2("classification22")
            .classification3("classification32")
            .classification4("classification42")
            .classification5("classification52")
            .unitofmeasurement("unitofmeasurement2")
            .decimalplaces(2)
            .assemblyunitof(2)
            .lmu(2)
            .itemspecfilepath("itemspecfilepath2")
            .itemimagepath("itemimagepath2")
            .accountcode("accountcode2")
            .accountid(2)
            .casepackqty(2)
            .defaultstocklocationid(2)
            .rackno("rackno2")
            .commissionempid(2)
            .checktypeid(2)
            .checktype("checktype2");
    }

    public static Inventory getInventoryRandomSampleGenerator() {
        return new Inventory()
            .id(longCount.incrementAndGet())
            .code(UUID.randomUUID().toString())
            .partnumber(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .type(intCount.incrementAndGet())
            .classification1(UUID.randomUUID().toString())
            .classification2(UUID.randomUUID().toString())
            .classification3(UUID.randomUUID().toString())
            .classification4(UUID.randomUUID().toString())
            .classification5(UUID.randomUUID().toString())
            .unitofmeasurement(UUID.randomUUID().toString())
            .decimalplaces(intCount.incrementAndGet())
            .assemblyunitof(intCount.incrementAndGet())
            .lmu(intCount.incrementAndGet())
            .itemspecfilepath(UUID.randomUUID().toString())
            .itemimagepath(UUID.randomUUID().toString())
            .accountcode(UUID.randomUUID().toString())
            .accountid(intCount.incrementAndGet())
            .casepackqty(intCount.incrementAndGet())
            .defaultstocklocationid(intCount.incrementAndGet())
            .rackno(UUID.randomUUID().toString())
            .commissionempid(intCount.incrementAndGet())
            .checktypeid(intCount.incrementAndGet())
            .checktype(UUID.randomUUID().toString());
    }
}
