package com.aaron.java8example.date.example;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Demo06 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期为:"+today);
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期为:"+nextWeek);
    }
}
