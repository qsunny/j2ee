package com.aaron.tools.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日期相关操作相关操作
 * @author aaron.qiu
 * @since 2015-2016
 */
@Deprecated
public class DateUtils {
	  public static String formatDate(java.util.Date date)	  {
		  return formatDateByFormat(date, "yyyy-MM-dd");
	  }

	  public static String formatDateByFormat(java.util.Date date, String format) {
	    String result = "";
	    if (date != null) {
	      try {
	        SimpleDateFormat sdf = new SimpleDateFormat(format);
	        result = sdf.format(date);
	      } catch (Exception ex) {
	        ex.printStackTrace();
	      }
	    }
	    return result;
	  }

	  public static java.util.Date parseDate(java.sql.Date date) {
		  return date;
	  }

	  public static java.util.Date parseDate(String date) throws ParseException {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    return df.parse(date);
	  }

	  public static java.util.Date parseDate(String date, String format) throws ParseException
	  {
	    SimpleDateFormat df = new SimpleDateFormat(format);
	    return df.parse(date);
	  }

	  public static java.sql.Date parseSqlDate(java.util.Date date) {
	    if (date != null) {
	      return new java.sql.Date(date.getTime());
	    }
	    return null;
	  }

	  public static String format(java.util.Date date, String format)
	  {
	    String result = "";
	    try {
	      if (date != null) {
	        DateFormat df = new SimpleDateFormat(format);
	        result = df.format(date);
	      }
	    } catch (Exception e) {
	    }
	    return result;
	  }

	  public static List<String> getDaysByWeek(java.util.Date date) throws Exception
	  {
	    date = getdate(format1(date));
	    List days = new ArrayList();
	    GregorianCalendar gcLast = (GregorianCalendar)Calendar.getInstance();
	    gcLast.setTime(date);
	    String firstWeek = getFirstWeekDay(date);
	    for (int i = 0; i < 7; i++) {
	      java.util.Date firstWeekDate = getdate1(firstWeek);
	      firstWeekDate = addDate(firstWeekDate, i);
	      int j = diffDate(date, firstWeekDate);
	      if (j < 0) break;
	      days.add(format1(firstWeekDate));
	    }

	    return days;
	  }

	  public static java.util.Date getNextWeek(java.util.Date date, int count) {
	    Calendar strDate = Calendar.getInstance();
	    strDate.setTime(date);
	    strDate.add(5, count * 7);
	    GregorianCalendar currentDate = new GregorianCalendar();
	    currentDate.set(strDate.get(1), strDate.get(2), strDate.get(5));

	    java.util.Date day = currentDate.getTime();
	    return day;
	  }

	  public static List<String> getDaysByDate(java.util.Date date)
	  {
	    List days = new ArrayList();
	    GregorianCalendar gcLast = (GregorianCalendar)Calendar.getInstance();
	    gcLast.setTime(date);
	    int dayss = getDay(date);
	    String monthStr = format(date, "yyyy-MM");
	    for (int i = 1; i <= dayss; i++) {
	      String day = new String();
	      if (i < 10)
	        day = monthStr + "-0" + i;
	      else {
	        day = monthStr + "-" + i;
	      }
	      days.add(day);
	    }
	    return days;
	  }

	  public static String getFirstWeekDay(java.util.Date theDate)
	  {
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(theDate);

	    calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);

	    calendar.setFirstDayOfWeek(2);
	    calendar.set(7, 2);
	    return format(calendar.getTime(), "yyyy-MM-dd") + " 00:00:00";
	  }

	  public static String getLastWeekDay(java.util.Date theDate)
	  {
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(theDate);

	    calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);

	    calendar.setFirstDayOfWeek(2);
	    calendar.set(7, 2);
	    return format(new java.util.Date(calendar.getTime().getTime() + 518400000L), "yyyy-MM-dd") + " 23:59:59";
	  }

	  public static String getFirstDay(java.util.Date theDate)
	  {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    GregorianCalendar gcLast = (GregorianCalendar)Calendar.getInstance();
	    gcLast.setTime(theDate);
	    gcLast.set(5, 1);
	    String day_first = df.format(gcLast.getTime());
	    StringBuffer str = new StringBuffer().append(day_first).append(" 00:00:00");

	    return str.toString();
	  }

	  public static java.util.Date getUpMonth(java.util.Date theDate, int month)
	    throws ParseException
	  {
	    Calendar c = Calendar.getInstance();
	    c.setTime(theDate);
	    c.add(2, month);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String time = format.format(c.getTime());
	    return format.parse(time);
	  }

	  public static String getLastDay(java.util.Date theDate)
	  {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    GregorianCalendar gcLast = (GregorianCalendar)Calendar.getInstance();
	    gcLast.setTime(theDate);
	    gcLast.set(5, gcLast.getActualMaximum(5));

	    String s = df.format(gcLast.getTime());
	    StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
	    return str.toString();
	  }

	  public static String getMinDay(java.util.Date theDate) {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String s = df.format(theDate);
	    StringBuffer str = new StringBuffer().append(s).append(" 00:00:00");
	    return str.toString();
	  }

	  public static String getMaxDay(java.util.Date theDate) {
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    String s = df.format(theDate);
	    StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");
	    return str.toString();
	  }

	  public static String format(java.util.Date date) {
	    return format(date, "yyyy/MM/dd");
	  }

	  public static String format1(java.util.Date date) {
	    return format(date, "yyyy-MM-dd");
	  }

	  public static int getYear(java.util.Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.get(1);
	  }

	  public static int getMonth(java.util.Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.get(2) + 1;
	  }

	  public static int getDay(java.util.Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.get(5);
	  }

	  public static int getHour(java.util.Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.get(11);
	  }

	  public static int getMinute(java.util.Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.get(12);
	  }

	  public static int getSecond(java.util.Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.get(13);
	  }

	  public static long getMillis(java.util.Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.getTimeInMillis();
	  }

	  public static int getWeek(java.util.Date date) {
	    Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    return c.get(3);
	  }

	  public static String getDate(java.util.Date date) {
	    return format(date, "yyyy/MM/dd");
	  }

	  public static String getDate(java.util.Date date, String formatStr) {
	    return format(date, formatStr);
	  }

	  public static String getTime(java.util.Date date) {
	    return format(date, "HH:mm:ss");
	  }

	  public static String getDateTime(java.util.Date date) {
	    return format(date, "yyyy-MM-dd HH:mm:ss");
	  }

	  public static java.util.Date addDate(java.util.Date date, int day)
	  {
	    Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR,day);
	    //c.setTimeInMillis(getMillis(date) + day * 24L * 3600L * 1000L);
	    return c.getTime();
	  }

	  public static int diffDate(java.util.Date date, java.util.Date date1)
	  {
	    return (int)((getMillis(date) - getMillis(date1)) / 86400000L);
	  }

	  public static Long diffDateTime(java.util.Date date, java.util.Date date1)
	  {
	    return Long.valueOf((getMillis(date) - getMillis(date1)) / 1000L);
	  }

	  public static java.util.Date getdate(String date) throws Exception {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    return sdf.parse(date);
	  }

	  public static java.util.Date getDate(String date, String format) throws Exception {
	    SimpleDateFormat sdf = new SimpleDateFormat(format);
	    return sdf.parse(date);
	  }

	  public static java.util.Date getJsonDate(String date) throws Exception {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	    return sdf.parse(date);
	  }

	  public static java.util.Date getdate1(String date) throws Exception {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdf.parse(date);
	  }

	  public static java.util.Date getMaxTimeByStringDate(String date) throws Exception
	  {
	    String maxTime = date + " 23:59:59";
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdf.parse(maxTime);
	  }

	  public static java.util.Date getCurrentDateTime()
	  {
	    java.util.Date date = new java.util.Date();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String result = getDateTime(date);
	    try {
	      return sdf.parse(result);
	    }
	    catch (ParseException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }

	  public static String getCurrentDateTimeToStr()
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    return sdf.format(getCurrentDateTime());
	  }

	  public static String getCurrentDateTimeToStr2() {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    return sdf.format(getCurrentDateTime());
	  }

	  public static Long getWmsupdateDateTime() {
	    Calendar cl = Calendar.getInstance();

	    return Long.valueOf(cl.getTimeInMillis());
	  }

	  public static Integer getLeftSeconds(String date) throws Exception {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date condition = sdf.parse(date);
	    long n = condition.getTime();
	    long s = sdf.parse(getCurrentDateTimeToStr2()).getTime();

	    return Integer.valueOf((int)((s - n) / 1000L));
	  }

	  public static void main(String[] args) throws Exception {
	    List days = getDatesBetweenTwoDate(getdate("2013-01-09"), getdate("2013-01-11"));

	    System.out.println(days.size());
	  }

	  public static List<java.util.Date> getDatesBetweenTwoDate(java.util.Date beginDate, java.util.Date endDate)
	  {
	    List lDate = new ArrayList();
	    lDate.add(beginDate);
	    Calendar cal = Calendar.getInstance();

	    cal.setTime(beginDate);
	    boolean bContinue = true;
	    while (bContinue)
	    {
	      cal.add(5, 1);

	      if (!endDate.after(cal.getTime())) break;
	      lDate.add(cal.getTime());
	    }

	    lDate.add(endDate);
	    return lDate;
	  }
}
