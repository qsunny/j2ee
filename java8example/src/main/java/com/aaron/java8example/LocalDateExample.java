package com.aaron.java8example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LocalDateExample {

    public void stringConvertLocalDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String date = "2016/08/18";
        //convert String to localDate
        LocalDate localDate = LocalDate.parse(date,formatter);
        localDate.plusYears(3);
        System.out.print(localDate);

    }

}
