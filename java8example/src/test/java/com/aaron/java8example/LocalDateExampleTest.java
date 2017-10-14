package com.aaron.java8example;

import com.aaron.java8example.date.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

/**
 * Created by Administrator on 2016/8/18.
 */
public class LocalDateExampleTest {

    private LocalDateExample localDateExample;

    @Before
    public void setUp() throws Exception {
        localDateExample = new LocalDateExample();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testStringConvertLocalDate() {
        localDateExample.stringConvertLocalDate();
    }

    @Test
    public void testStringConvertLocalDate2() {
        localDateExample.stringConvertLocalDate2();
    }

    @Test
    public void testStringConvertLocalDate3() {
        localDateExample.stringConvertLocalDate3();
    }

    @Test
    public void testStringConvertLocalDate4() {
        localDateExample.stringConvertLocalDate4();
    }

    @Test
    public void testStringConvertLocalDate5() {
        localDateExample.stringConvertLocalDate5();
    }

    @Test
    public void testStringConvertLocalDate6() {
        localDateExample.stringConvertLocalDate6();
    }

    @Test
    public void testStringConvertLocalDate7() {
        localDateExample.stringConvertLocalDate7();
    }

    @Test
    public void testStringConvertLocalDate8() {
        localDateExample.stringConvertLocalDate8();
    }

    @Test
    public void testSimpleCalendar() {
        localDateExample.simpleCalendar();
    }

    @Test
    public void testCompareDate1() throws ParseException {
        localDateExample.compareDate1();
    }

    @Test
    public void testCompareDate2() throws ParseException {
        localDateExample.compareDate2();
    }

    @Test
    public void testCompareDate3() throws ParseException {
        localDateExample.compareDate3();
    }

    @Test
    public void testCompareDateCalendar() throws ParseException {
        localDateExample.compareDateCalendar();
    }

    @Test
    public void testGetCurrentDateTime() {
        GetCurrentDateTime.getCurrDateTime();
    }

    @Test
    public void testGetTimeStamp() {
        TimeStampExample.getTimeStamp();
    }

    @Test
    public void testCalculateExecuteTime1() throws InterruptedException {
        ExecutionTime.calculateExecuteTime3();
    }

    @Test
    public void testLocalDateTimeFormatter() {
        LocalDateTimeExample.format1();
        LocalDateTimeExample.format2();
    }
    @Test
    public void testInstantExample() {
        InstantExample.convertInstantToLocalDateTime();
        InstantExample.localDateTimeToConvertInstant();
        InstantExample.convertzonedDateTimeToInstant();
    }

    @Test
    public void testDisplayZoneAndOffSet() {
        DisplayZoneAndOffSet.show();
    }

    @Test
    public void testNextChrismas() {
        LocalDate localDate = LocalDate.now();
        System.out.println("current date : " + localDate);

        localDate = localDate.with(new NextChristmas());
        System.out.println("Next Christmas : " + localDate);

        localDate = localDate.with(
                temporal -> temporal.with(ChronoField.MONTH_OF_YEAR, 12).with(ChronoField.DAY_OF_MONTH, 25)
        );

    }



}