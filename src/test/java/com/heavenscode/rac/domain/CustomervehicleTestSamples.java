package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CustomervehicleTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Customervehicle getCustomervehicleSample1() {
        return new Customervehicle()
            .id(1L)
            .customerid(1)
            .vehiclenumber("vehiclenumber1")
            .categoryid(1)
            .categoryname("categoryname1")
            .typeid(1)
            .typename("typename1")
            .makeid(1)
            .makename("makename1")
            .model("model1")
            .yom("yom1")
            .customercode("customercode1")
            .remarks("remarks1")
            .servicecount(1)
            .engNo("engNo1")
            .chaNo("chaNo1")
            .milage("milage1")
            .lmu(1)
            .nextgearoilmilage("nextgearoilmilage1")
            .nextmilage("nextmilage1")
            .serviceperiod(1);
    }

    public static Customervehicle getCustomervehicleSample2() {
        return new Customervehicle()
            .id(2L)
            .customerid(2)
            .vehiclenumber("vehiclenumber2")
            .categoryid(2)
            .categoryname("categoryname2")
            .typeid(2)
            .typename("typename2")
            .makeid(2)
            .makename("makename2")
            .model("model2")
            .yom("yom2")
            .customercode("customercode2")
            .remarks("remarks2")
            .servicecount(2)
            .engNo("engNo2")
            .chaNo("chaNo2")
            .milage("milage2")
            .lmu(2)
            .nextgearoilmilage("nextgearoilmilage2")
            .nextmilage("nextmilage2")
            .serviceperiod(2);
    }

    public static Customervehicle getCustomervehicleRandomSampleGenerator() {
        return new Customervehicle()
            .id(longCount.incrementAndGet())
            .customerid(intCount.incrementAndGet())
            .vehiclenumber(UUID.randomUUID().toString())
            .categoryid(intCount.incrementAndGet())
            .categoryname(UUID.randomUUID().toString())
            .typeid(intCount.incrementAndGet())
            .typename(UUID.randomUUID().toString())
            .makeid(intCount.incrementAndGet())
            .makename(UUID.randomUUID().toString())
            .model(UUID.randomUUID().toString())
            .yom(UUID.randomUUID().toString())
            .customercode(UUID.randomUUID().toString())
            .remarks(UUID.randomUUID().toString())
            .servicecount(intCount.incrementAndGet())
            .engNo(UUID.randomUUID().toString())
            .chaNo(UUID.randomUUID().toString())
            .milage(UUID.randomUUID().toString())
            .lmu(intCount.incrementAndGet())
            .nextgearoilmilage(UUID.randomUUID().toString())
            .nextmilage(UUID.randomUUID().toString())
            .serviceperiod(intCount.incrementAndGet());
    }
}
