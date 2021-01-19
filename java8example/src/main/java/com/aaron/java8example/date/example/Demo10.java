package com.aaron.java8example.date.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Demo10 {
    public static void main(String[] args) {
        // Date and time with timezone in Java 8
        ZoneId america = ZoneId.of("America/New_York");
//        ZoneId america = ZoneId.of("Asia/Shanghai");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);
    }
}
