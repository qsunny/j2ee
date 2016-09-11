package com.aaron.tools.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * 用户中心Cookie相关的操作
 * @Author:Aaron.Qiu
 * @Since:2014-07-23
 * Copyright (c) 2015 ~ 2016 版权所有
 */
public class Cookies {
    
	private static int iDefaultValidSecond = -1;
	
    public Cookies() {}
    
    /**
     * Cross domain Put cookie to the client
     * @param response 输出
     * @param name Cookie的名字
     * @param value Cookie的值
     */
    public static void crossDomainPut(HttpServletResponse response,
                           String name,
                           String value) {
    	System.out.println("==========="+name+"="+value);
    	setP3PHeader(response);
    	put(response,name,value,"/",iDefaultValidSecond);
    }
    
    /**
     * Cross domain Put cookie to the client
     * @param response 输出
     * @param name Cookie的名字
     * @param value Cookie的值
     * @param ttl Cookie生存秒数
     */
    public static void crossDomainPut(HttpServletResponse response,
            String name,String value,int ttl) {
		System.out.println("==========="+name+"="+value);
		setP3PHeader(response);
		put(response, name, value, ttl);
	}
    
    private static void setP3PHeader(HttpServletResponse response) {
		response.setHeader("P3P", "CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\"");
    }
    
    /**
     * Put cookie to the client
     * @param response 输出
     * @param name Cookie的名字
     * @param value Cookie的值
     */
    public static void put(HttpServletResponse response,
                           String name,
                           String value) {
    	System.out.println("==========="+name+"="+value);
        put(response,name,value,"/",iDefaultValidSecond);
    }

    /**
     * 设定一个Cookie,有生存时间设定,单位为秒
     * @param response 输出
     * @param name  Cookie的名称
     * @param value  Cookie的值
     * @param ttl Cookie生存秒数
     */
    public static void put(HttpServletResponse response,String name,String value,int ttl) {
        put(response , name , value , "/" , ttl);
    }
    /**
     * 
     * @param response
     * @param name
     * @param value
     * @param path
     * @param ttl 秒
     */
    public static void put(HttpServletResponse response, String name,String value,String path ,int ttl) {
		try {
			Cookie cookie = new Cookie(name, encode(value));
			cookie.setPath(path);
			cookie.setMaxAge(ttl); // 365天的秒数:31536000
			response.addCookie(cookie);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    /**
     * get cookie from client
     * @param request
     * @param name
     * @return
     */
    public static String get(HttpServletRequest request,
                             String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return "";

        String result = "";
        try {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    result = cookies[i].getValue();
                    break;
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return decode(result);
    }

    /**
     * 清除Cookie
     * @param response HttpServletResponse
     * @param name String
     */
    public static void remove(HttpServletResponse response, String name) {
        put(response, name, null, 0);
    }

    /**
     * 清除所有的Cookie
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public static void removeAll(HttpServletRequest request,
                                 HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            try {
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = new Cookie(cookies[i].getName(), null);
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

    /**
     * 对给定字符进行 URL 编码
     * @param value String
     * @return String
     */
    private static String encode(String value) {
        String result = "";
        if (!isEmpty(value)){
            try {
                result = java.net.URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            	e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 对给定字符进行 URL 解码
     * @param value String
     * @return String
     */
    private static String decode(String value) {
        String result = "";
        if (!isEmpty(value)){
            try {
                result = java.net.URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
            	e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * 判断是否为空，为空返回true
     * @param value String
     * @return boolean
     */
    private static boolean isEmpty(String value){
        if(value == null || value.trim().equals(""))
            return true;
        else
            return false;
    }
}
