package com.aaron.java8example.date.example;

import java.time.LocalDate;

public class Demo12 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        if(today.isLeapYear()){
            System.out.println("This year is Leap year");
        }else {
            System.out.println("2021 is not a Leap year");
        }
    }
}
