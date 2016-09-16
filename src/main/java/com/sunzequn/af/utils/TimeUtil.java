package com.sunzequn.af.utils;

import java.util.Date;

/**
 * Created by sloriac on 16-9-1.
 */
public class TimeUtil {

    private static long startTime;
    private static long endTime;

    private TimeUtil() {
    }

    public static void start() {
        startTime = new Date().getTime();
    }

    public static void end() {
        endTime = new Date().getTime();
    }

    public static long duration() {
        end();
        return endTime - startTime;
    }

    public static void print() {
        System.out.println("cost: " + duration() + " milliseconds");
    }
}
