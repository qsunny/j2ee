package com.aaron.java8example.date;

import java.time.LocalDate;
import java.time.chrono.MinguoDate;

/**
 * Created by Aaron.qiu on 2017/10/15.
 */
public class MinguoDateExample {
    public static void showcase() {

        // LocalDate -> MinguoDate
        System.out.println("Example 1...");
        LocalDate localDate = LocalDate.of(1912, 1, 1);
        MinguoDate minguo = MinguoDate.from(localDate);
        System.out.println("LocalDate : " + localDate); //1912-01-01
        System.out.println("MinguoDate : " + minguo);   //1-01-01

        // MinguoDate -> LocalDate
        System.out.println("\nExample 2...");

        MinguoDate minguo2 = MinguoDate.of(105, 8, 24);
        //LocalDate localDate = LocalDate.ofEpochDay(minguo2.toEpochDay());
        LocalDate localDate2 = LocalDate.from(minguo2);
        System.out.println("MinguoDate : " + minguo2);   //105-08-24
        System.out.println("LocalDate : " + localDate2); //2016-08-24
    }
}
