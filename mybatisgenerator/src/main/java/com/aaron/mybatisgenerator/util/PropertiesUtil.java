package com.aaron.mybatisgenerator.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil
{
    private static Properties properties = new Properties();

    public static String getProperties(String key)
            throws Exception
    {
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
        String value = properties.getProperty(key);
        value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
        return value;
    }

    public static void setProperties(String key, String value)
            throws Exception
    {
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
        OutputStream fos = new FileOutputStream(new File(ClassLoader.getSystemResource("config.properties").getPath()));
        properties.setProperty(key, value);
        properties.store(fos, "Update '" + value + "' value");
    }
}
