package com.aaron.java8example.date;

import java.time.*;

/**
 * Created by Aaron.Qiu on 2017/10/14.
 */
public class InstantExample {
    //Instant -> LocalDateTime
    public static void convertInstantToLocalDateTime() {
        // Parse a ISO 8601 Date directly
        //Instant instant = Instant.parse("2016-08-18T06:17:10.225Z");

        Instant instant = Instant.now();

        System.out.println("Instant : " + instant);

        //Convert instant to LocalDateTime, no timezone, add a zero offset / UTC+0
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        System.out.println("LocalDateTime : " + ldt);
    }

    //LocalDateTime -> Instant
    public static void localDateTimeToConvertInstant() {
        // Parse a ISO 8601 Date directly
        //Instant instant = Instant.parse("2016-08-18T06:17:10.225Z");

        Instant instant = Instant.now();

        System.out.println("Instant : " + instant);

        //Convert instant to LocalDateTime, no timezone, add a zero offset / UTC+0
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        System.out.println("LocalDateTime : " + ldt);
    }

    //Instant -> ZonedDateTime
    public static void instantToConvertzonedDateTime() {
        // Z = UTC+0
        Instant instant = Instant.now();

        System.out.println("Instant : " + instant);

        // Japan = UTC+9
        ZonedDateTime jpTime = instant.atZone(ZoneId.of("Asia/Tokyo"));

        System.out.println("ZonedDateTime : " + jpTime);

        System.out.println("OffSet : " + jpTime.getOffset());
    }

    //ZonedDateTime -> Instant
    public static void convertzonedDateTimeToInstant() {
        LocalDateTime dateTime = LocalDateTime.of(2018, Month.AUGUST, 18, 6, 57, 38);

        // UTC+9
        ZonedDateTime jpTime = dateTime.atZone(ZoneId.of("Asia/Tokyo"));

        System.out.println("ZonedDateTime : " + jpTime);

        // Convert to instant UTC+0/Z , java.time helps to reduce 9 hours
        Instant instant = jpTime.toInstant();

        System.out.println("Instant : " + instant);

    }
}
