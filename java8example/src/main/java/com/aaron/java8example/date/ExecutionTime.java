package com.aaron.java8example.date;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Aaron.Qiu on 2017/10/14.
 */
public class ExecutionTime {
    private static void calculation() throws InterruptedException {

        //Sleep 2 seconds
        TimeUnit.SECONDS.sleep(2);

    }

    //System.nanoTime()
    public static void calculateExecuteTime1() throws InterruptedException {
        //start
        long lStartTime = System.nanoTime();

        //task
        calculation();

        //end
        long lEndTime = System.nanoTime();

        //time elapsed
        long output = lEndTime - lStartTime;

        System.out.println("Elapsed time in milliseconds: " + output / 1000000);
    }

    //System.currentTimeMillis()
    public static void calculateExecuteTime2() throws InterruptedException {
        long lStartTime = System.currentTimeMillis();

        calculation();

        long lEndTime = System.currentTimeMillis();

        long output = lEndTime - lStartTime;

        System.out.println("Elapsed time in milliseconds: " + output);
    }

    //Instant.now().toEpochMilli()
    public static void calculateExecuteTime3() throws InterruptedException {
        long lStartTime = Instant.now().toEpochMilli();

        calculation();

        long lEndTime = Instant.now().toEpochMilli();

        long output = lEndTime - lStartTime;

        System.out.println("Elapsed time in milliseconds: " + output);
    }

    //Date().getTime()
    public static void calculateExecuteTime4() throws InterruptedException {
        long lStartTime = new Date().getTime();

        calculation();

        long lEndTime = new Date().getTime();

        long output = lEndTime - lStartTime;

        System.out.println("Elapsed time in milliseconds: " + output);
    }

}
