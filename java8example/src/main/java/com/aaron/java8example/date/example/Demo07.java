package com.aaron.java8example.date.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Demo07 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();

        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("一年前的日期 : " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("一年后的日期:"+nextYear);
    }
}
