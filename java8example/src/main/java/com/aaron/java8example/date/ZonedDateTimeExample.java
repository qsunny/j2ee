package com.aaron.java8example.date;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Aaron.qiu on 2017/10/15.
 */
public class ZonedDateTimeExample {
    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";

    public static void zonedDateTimeExample1() {

        String dateInString = "22-1-2017 10:15:55 AM";
        LocalDateTime ldt = LocalDateTime.parse(dateInString, DateTimeFormatter.ofPattern(DATE_FORMAT));

        ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
        System.out.println("TimeZone : " + singaporeZoneId);

        //LocalDateTime + ZoneId = ZonedDateTime
        ZonedDateTime asiaZonedDateTime = ldt.atZone(singaporeZoneId);
        System.out.println("Date (Singapore) : " + asiaZonedDateTime);

        ZoneId newYokZoneId = ZoneId.of("America/New_York");
        System.out.println("TimeZone : " + newYokZoneId);

        ZonedDateTime nyDateTime = asiaZonedDateTime.withZoneSameInstant(newYokZoneId);
        System.out.println("Date (New York) : " + nyDateTime);

        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
        System.out.println("\n---DateTimeFormatter---");
        System.out.println("Date (Singapore) : " + format.format(asiaZonedDateTime));
        System.out.println("Date (New York) : " + format.format(nyDateTime));

    }
}
