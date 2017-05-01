package gdev.projectbattle.math;

import java.util.Random;

public class RandUtils {
    static Random r = new Random();

    public static float floatRange(float min, float max) {
        return r.nextFloat() * (max - min) + min;
    }

    public static double doubleRange(double min, double max) {
        return r.nextDouble() * (max - min) + min;
    }

    public static int intRange(int min, int max) {
        return r.nextInt((max - min) + 1) + min;
    }

    public static double rand10() {
        Random r = new Random();
        return 10*r.nextDouble();
    }
}
