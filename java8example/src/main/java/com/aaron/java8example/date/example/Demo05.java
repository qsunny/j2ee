package com.aaron.java8example.date.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.MonthDay;

public class Demo05 {
    public static void main(String[] args) {
        LocalDate date1 = LocalDate.now();

        LocalDate date2 = LocalDate.of(2018,2,6);
        MonthDay birthday = MonthDay.of(date2.getMonth(),date2.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(date1);

        if(currentMonthDay.equals(birthday)){
            System.out.println("是你的生日");
        }else{
            System.out.println("你的生日还没有到");
        }

        LocalTime time = LocalTime.now();
        System.out.println("获取当前的时间,不含有日期:"+time);

        LocalTime newTime = time.plusHours(3);
        System.out.println("三个小时后的时间为:"+newTime);
    }
}
