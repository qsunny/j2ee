package com.aaron.java8example.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by aaron on 2016/8/18.
 * https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
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

    public void stringConvertLocalDate2() {
        String date = "2016-08-16";

        //default, ISO_LOCAL_DATE
        LocalDate localDate = LocalDate.parse(date);

        System.out.println(localDate);
    }

    public void stringConvertLocalDate3() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        String date = "16-Aug-2018";

        LocalDate localDate = LocalDate.parse(date, formatter);

        System.out.println(localDate);  //default, print ISO_LOCAL_DATE

        System.out.println(formatter.format(localDate));
    }

    public void stringConvertLocalDate4() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        String date = "16/10/2018";

        LocalDate localDate = LocalDate.parse(date, formatter);
        System.out.println(localDate);

        System.out.println(formatter.format(localDate));
    }

    public void stringConvertLocalDate5() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy");

        String date = "Tue, Aug 16 2016";

        LocalDate localDate = LocalDate.parse(date, formatter);

        System.out.println(localDate);

        System.out.println(formatter.format(localDate));
    }

    public void stringConvertLocalDate6() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a");

        String date = "Tuesday, Aug 16, 2016 12:10:56 PM";

        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

        System.out.println(localDateTime);

        System.out.println(formatter.format(localDateTime));
    }

    public void stringConvertLocalDate7() {
        String dateInString = "2018-10-16T15:23:01Z";

        Instant instant = Instant.parse(dateInString);

        System.out.println("Instant : " + instant);

        //get date time only
        LocalDateTime result = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));

        //get localdate
        System.out.println("LocalDate : " + result.toLocalDate());

        //get date time + timezone
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime);

        //get date time + timezone
        ZonedDateTime zonedDateTime2 = instant.atZone(ZoneId.of("Europe/Athens"));
        System.out.println(zonedDateTime2);
    }

    public void stringConvertLocalDate8() {
        String date = "2018-10-16T10:15:30+08:00";

        ZonedDateTime result = ZonedDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);

        System.out.println("ZonedDateTime : " + result);

        System.out.println("TimeZone : " + result.getZone());

        LocalDate localDate = result.toLocalDate();

        System.out.println("LocalDate : " + localDate);
    }

    public void simpleCalendar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
        Calendar calendar = new GregorianCalendar(2018,10,14,13,24,56);

        int year       = calendar.get(Calendar.YEAR);
        int month      = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);

        int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
        int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
        int minute     = calendar.get(Calendar.MINUTE);
        int second     = calendar.get(Calendar.SECOND);
        int millisecond= calendar.get(Calendar.MILLISECOND);

        System.out.println(sdf.format(calendar.getTime()));

        System.out.println("year \t\t: " + year);
        System.out.println("month \t\t: " + month);
        System.out.println("dayOfMonth \t: " + dayOfMonth);
        System.out.println("dayOfWeek \t: " + dayOfWeek);
        System.out.println("weekOfYear \t: " + weekOfYear);
        System.out.println("weekOfMonth \t: " + weekOfMonth);

        System.out.println("hour \t\t: " + hour);
        System.out.println("hourOfDay \t: " + hourOfDay);
        System.out.println("minute \t\t: " + minute);
        System.out.println("second \t\t: " + second);
        System.out.println("millisecond \t: " + millisecond);

        //update a date
        calendar.set(Calendar.YEAR, 2014);
        calendar.set(Calendar.MONTH, 11);
        calendar.set(Calendar.MINUTE, 33);

        System.out.println("#2. " + sdf.format(calendar.getTime()));

        //add one month
        calendar.add(Calendar.MONTH, 1);
        System.out.println("Date : " + sdf.format(calendar.getTime()));

        //subtract 10 days
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        System.out.println("Date : " + sdf.format(calendar.getTime()));
    }

    public static void compareDate1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2009-12-31");
        Date date2 = sdf.parse("2010-01-31");

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        if (date1.compareTo(date2) > 0) {
            System.out.println("Date1 is after Date2");
        } else if (date1.compareTo(date2) < 0) {
            System.out.println("Date1 is before Date2");
        } else if (date1.compareTo(date2) == 0) {
            System.out.println("Date1 is equal to Date2");
        } else {
            System.out.println("How to get here?");
        }
    }

    public static void compareDate2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2009-12-31");
        Date date2 = sdf.parse("2010-01-31");

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        if (date1.after(date2)) {
            System.out.println("Date1 is after Date2");
        }

        if (date1.before(date2)) {
            System.out.println("Date1 is before Date2");
        }

        if (date1.equals(date2)) {
            System.out.println("Date1 is equal Date2");
        }
    }

    public static void compareDate3() throws ParseException {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1 = LocalDate.of(2009, 12, 31);
        LocalDate date2 = LocalDate.of(2010, 01, 31);

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        System.out.println("Is...");
        if (date1.isAfter(date2)) {
            System.out.println("Date1 is after Date2");
        }

        if (date1.isBefore(date2)) {
            System.out.println("Date1 is before Date2");
        }

        if (date1.isEqual(date2)) {
            System.out.println("Date1 is equal Date2");
        }

        System.out.println("CompareTo...");
        if (date1.compareTo(date2) > 0) {
            System.out.println("Date1 is after Date2");

        } else if (date1.compareTo(date2) <  0) {
            System.out.println("Date1 is before Date2");

        } else if (date1.compareTo(date2) == 0) {

            System.out.println("Date1 is equal to Date2");

        } else {

            System.out.println("How to get here?");

        }
    }

    public static void compareDateCalendar() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2009-12-31");
        Date date2 = sdf.parse("2010-01-31");

        System.out.println("date1 : " + sdf.format(date1));
        System.out.println("date2 : " + sdf.format(date2));

        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        if (cal1.after(cal2)) {
            System.out.println("Date1 is after Date2");
        }

        if (cal1.before(cal2)) {
            System.out.println("Date1 is before Date2");
        }

        if (cal1.equals(cal2)) {
            System.out.println("Date1 is equal Date2");
        }

    }

}
