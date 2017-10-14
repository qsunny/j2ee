package com.aaron.java8example.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by Aaron.qiu on 2017/10/15.
 */
public class DifferentTimeZoneExample {
    /**
     * ---Flight Detail---
     * Kuala Lumpur (KUL) -> Tokyo Haneda (HND)
     * Flight Duration : 7 hours
     * (KUL-Depart) 1430, 22 Aug 2016 ->  2230, 22 Aug 2016 (HND-Arrive)
     */
    public static void zonedDateTime1() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");

        LocalDateTime ldt = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30);
        System.out.println("LocalDateTime : " + format.format(ldt));

        //UTC+8
        ZonedDateTime klDateTime = ldt.atZone(ZoneId.of("Asia/Kuala_Lumpur"));
        System.out.println("Depart : " + format.format(klDateTime));

        //UTC+9 and flight duration = 7 hours
        ZonedDateTime japanDateTime = klDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo")).plusHours(7);
        System.out.println("Arrive : " + format.format(japanDateTime));

        System.out.println("\n---Detail---");
        System.out.println("Depart : " + klDateTime);
        System.out.println("Arrive : " + japanDateTime);

    }

    /**
     * France, Paris -> -05:00
     * ---Flight Detail---
     France, Paris -> UTC-05:00
     Flight Duration : 8 hours 10 minutes
     (Depart) 1430, 22 Aug 2016 ->  1540, 22 Aug 2016 (Arrive)
     */
    public static void zonedDateTime2() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");

        //Convert String to LocalDateTime
        String date = "2016-08-22 14:30";
        LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println("LocalDateTime : " + format.format(ldt));

        //Paris, 2016 Apr-Oct = DST, UTC+2, other months UTC+1
        //UTC+2
        ZonedDateTime parisDateTime = ldt.atZone(ZoneId.of("Europe/Paris"));
        System.out.println("Depart : " + format.format(parisDateTime));

        //hard code a zoneoffset like this, UTC-5
        ZoneOffset nyOffSet = ZoneOffset.of("-05:00");
        ZonedDateTime nyDateTime = parisDateTime.withZoneSameInstant(nyOffSet).plusHours(8).plusMinutes(10);
        System.out.println("Arrive : " + format.format(nyDateTime));

        System.out.println("\n---Detail---");
        System.out.println("Depart : " + parisDateTime);
        System.out.println("Arrive : " + nyDateTime);
    }
}
