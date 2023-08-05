package pl.qlnus.utils;

import java.util.concurrent.ThreadLocalRandom;

public final class RandomUtil {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static int getRandom(int min, int max) {
        return RANDOM.nextInt(min, max);
    }

    public static int getRandom(int index) {
        return RANDOM.nextInt(index);
    }
}