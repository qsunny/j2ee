package com.aaron.java8example.date;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * Created by Aaron.qiu on 2017/10/15.
 */
public class PeriodExample {
    public static void showcase1() {

        System.out.println("--- Examples --- ");

        Period tenDays = Period.ofDays(10);
        System.out.println(tenDays.getDays()); //10

        Period oneYearTwoMonthsThreeDays = Period.of(1, 2, 3);
        System.out.println(oneYearTwoMonthsThreeDays.getYears());   //1
        System.out.println(oneYearTwoMonthsThreeDays.getMonths());  //2
        System.out.println(oneYearTwoMonthsThreeDays.getDays());    //3

        System.out.println("\n--- Period.between --- ");
        LocalDate oldDate = LocalDate.of(1982, Month.AUGUST, 31);
        LocalDate newDate = LocalDate.of(2016, Month.NOVEMBER, 9);

        System.out.println(oldDate);
        System.out.println(newDate);

        // check period between dates
        Period period = Period.between(oldDate, newDate);

        System.out.print(period.getYears() + " years,");
        System.out.print(period.getMonths() + " months,");
        System.out.print(period.getDays() + " days");

    }
}
