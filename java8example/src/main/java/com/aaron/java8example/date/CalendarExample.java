package com.aaron.java8example.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Aaron.qiu on 2017/10/15.
 */
public class CalendarExample {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static void showcase1() {

        Date currentDate = new Date();
        System.out.println(dateFormat.format(currentDate));

        // convert date to calendar
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);

        // manipulate date
        c.add(Calendar.YEAR, 1);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, 1); //same with c.add(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.HOUR, 1);
        c.add(Calendar.MINUTE, 1);
        c.add(Calendar.SECOND, 1);

        // convert calendar to date
        Date currentDatePlusOne = c.getTime();

        System.out.println(dateFormat.format(currentDatePlusOne));

    }
}
