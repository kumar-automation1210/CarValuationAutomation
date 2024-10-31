package com.valuation.utils;

import java.util.Random;

public class TestUtil {

    public static String getRandomMileage() {
        Random random = new Random();
        return String.valueOf(10000 + random.nextInt(99999)) ;
    }
}
