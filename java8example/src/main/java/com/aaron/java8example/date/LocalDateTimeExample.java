package com.aaron.java8example.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by Aaron.Qiu on 2017/10/14.
 */
public class LocalDateTimeExample {
    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static void format1() {
        //Get current date time
        LocalDateTime now = LocalDateTime.now();

        System.out.println("Before : " + now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

        System.out.println("After : " + formatDateTime);
    }

    public static void format2() {
        String now = "2017-11-09 10:30";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);

        System.out.println("Before : " + now);

        System.out.println("After : " + formatDateTime);

        System.out.println("After : " + formatDateTime.format(formatter));

    }

    public static void plusYearMonthDays() {

        // Get current date
        Date currentDate = new Date();
        System.out.println("date : " + dateFormat.format(currentDate));

        // convert date to localdatetime
        LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println("localDateTime : " + dateFormat8.format(localDateTime));

        // plus one
        localDateTime = localDateTime.plusYears(1).plusMonths(1).plusDays(1);
        localDateTime = localDateTime.plusHours(1).plusMinutes(2).minusMinutes(1).plusSeconds(1);

        // convert LocalDateTime to date
        Date currentDatePlusOneDay = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        System.out.println("\nOutput : " + dateFormat.format(currentDatePlusOneDay));

    }

}
