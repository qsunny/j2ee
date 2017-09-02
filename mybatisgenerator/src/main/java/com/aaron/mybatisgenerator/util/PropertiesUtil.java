package com.aaron.mybatisgenerator.util;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil
{
    private Properties properties = new Properties();

    private String configPath = "";

    public PropertiesUtil() {
        String path = "";
        try {
            path = ClassLoader.getSystemResource("log4j.properties").getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("path==="+path);
        if(path!=null&&path.lastIndexOf("/lib")>=0) {
            path = path.replaceAll("file:/","");
            configPath = path.substring(0, path.lastIndexOf("/lib")) + "/config/";
        }

        System.out.println("class path = " + configPath);
        try {
            if(!"".equals(configPath)) {
                configPath = configPath+"config.properties";
                System.out.println("file absolute path = " + configPath);
                //FileInputStream fis = new FileInputStream(new File("E:/workspaceidea/j2ee/mybatisgenerator/build/install/mybatisgenerator/config/config.properties"));
                FileInputStream fis = new FileInputStream(new File(configPath));
                properties.load(fis);
                fis.close();
            } else {
                properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                //防止没有编译的情况下获取不到class path路径下的properties
                properties.load(ClassLoader.getSystemResourceAsStream("src/main/resources/config.properties"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public String getProperties(String key)
            throws Exception
    {
//        String path1 = ClassLoader.getSystemResource("log4j.properties").getPath();
//        System.out.println(path1);
//        System.out.println("class path = " + path1.substring(0,path1.lastIndexOf("/")));
//
//        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
        String value = properties.getProperty(key);
        value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
        return value;
    }

    public void setProperties(String key, String value)
            throws Exception
    {
        properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
        OutputStream fos = new FileOutputStream(new File(ClassLoader.getSystemResource("config.properties").getPath()));
        properties.setProperty(key, value);
        properties.store(fos, "Update '" + value + "' value");
    }
}
