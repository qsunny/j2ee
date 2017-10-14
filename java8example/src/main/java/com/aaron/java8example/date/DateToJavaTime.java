package com.aaron.java8example.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

/**
 * Created by Aaron.qiu on 2017/10/15.
 */
public class DateToJavaTime {


    // Date -> java.time
    public static void convertDateToJavaTime1() {

        //Asia/Kuala_Lumpur +8
        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println("System Default TimeZone : " + defaultZoneId);

        //toString() append +8 automatically.
        Date date = new Date();
        System.out.println("date : " + date);

        //1. Convert Date -> Instant
        Instant instant = date.toInstant();
        System.out.println("instant : " + instant); //Zone : UTC+0

        //2. Instant + system default time zone + toLocalDate() = LocalDate
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        System.out.println("localDate : " + localDate);

        //3. Instant + system default time zone + toLocalDateTime() = LocalDateTime
        LocalDateTime localDateTime = instant.atZone(defaultZoneId).toLocalDateTime();
        System.out.println("localDateTime : " + localDateTime);

        //4. Instant + system default time zone = ZonedDateTime
        ZonedDateTime zonedDateTime = instant.atZone(defaultZoneId);
        System.out.println("zonedDateTime : " + zonedDateTime);
    }

    //java.time -> Date
    public static void convertTimeToDate() {

        //Asia/Kuala_Lumpur +8
        ZoneId defaultZoneId = ZoneId.systemDefault();
        System.out.println("System Default TimeZone : " + defaultZoneId);

        LocalDate localDate = LocalDate.of(2016, 8, 19);
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        System.out.println("\n1. LocalDate -> Date");
        System.out.println("localDate : " + localDate);
        System.out.println("date : " + date);

        LocalDateTime localDateTime = LocalDateTime.of(2016,8,19,21,46,31);
        Date date2 = Date.from(localDateTime.atZone(defaultZoneId).toInstant());
        System.out.println("\n2. LocalDateTime -> Date");
        System.out.println("localDateTime : " + localDateTime);
        System.out.println("date2 : " + date2);

        ZonedDateTime zonedDateTime = localDateTime.atZone(defaultZoneId);
        Date date3 = Date.from(zonedDateTime.toInstant());
        System.out.println("\n3. ZonedDateTime -> Date");
        System.out.println("zonedDateTime : " + zonedDateTime);
        System.out.println("date3 : " + date3);

    }



}
