package com.aaron.java8example.date.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Demo09 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        LocalDate tomorrow = LocalDate.of(2021,2,6);
        if(tomorrow.isAfter(today)){
            System.out.println("之后的日期:"+tomorrow);
        }

        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if(yesterday.isBefore(today)){
            System.out.println("之前的日期:"+yesterday);
        }
    }
}
