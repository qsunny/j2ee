package com.aaron.mybatisgenerator.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class DateUtil
{
    public static String DateToString(Date date, String format)
    {
        if (date == null)
        {
            date = new Date();
        }
        if (StringUtils.isEmpty(format))
        {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        return simpleDateFormat.format(date);
    }

    public static Date StringToDate(String dateStr, String format)
    {
        if (StringUtils.isEmpty(format))
        {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(dateStr);
            if (date.getTime() <= System.currentTimeMillis()) return date;
            date = new Date();
        }
        catch (ParseException e1)
        {
            return null;
        }
        return date;
    }

    public static String getCurrentDate(String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        return sdf.format(new Date());
    }

    public static Date getNowUtilDate()
    {
        return new Date();
    }

    public static Date getNowSqlDate()
    {
        return new Date(System.currentTimeMillis());
    }

    public static Timestamp getNowTimestamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}
