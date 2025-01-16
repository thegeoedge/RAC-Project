package com.heavenscode.rac.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AutocarejobinimagesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Autocarejobinimages getAutocarejobinimagesSample1() {
        return new Autocarejobinimages().id(1L).jobid(1).imagefolder("imagefolder1").imagename("imagename1");
    }

    public static Autocarejobinimages getAutocarejobinimagesSample2() {
        return new Autocarejobinimages().id(2L).jobid(2).imagefolder("imagefolder2").imagename("imagename2");
    }

    public static Autocarejobinimages getAutocarejobinimagesRandomSampleGenerator() {
        return new Autocarejobinimages()
            .id(longCount.incrementAndGet())
            .jobid(intCount.incrementAndGet())
            .imagefolder(UUID.randomUUID().toString())
            .imagename(UUID.randomUUID().toString());
    }
}
