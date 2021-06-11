package com.aaron.base.utils;


import com.aaron.base.enums.BaseResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @Author:Aaron.Qiu
 * @Since:2015-04-12
 * Copyright (c) 2015 ~ 2021 版权所有
 */
public class CommUtils {
	
	public static boolean isEmpty(String str){
		if(null == str || "".equals(str)){
			return true;
		}else{
			return false;
		}
	}

	public static boolean isNotEmpty(String str){
		return !CommUtils.isEmpty(str);
	}
	
	/**
	 * 转化一个对象名称为去掉前后空格,带缺省值
	 * 
	 * @param obj
	 * @return String
	 */
	public static String toStr(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString().trim();
		}
	}
	
	/**
	 * 将一个对象字串转化为一个数字
	 * @param obj Object
	 * @return int
	 */
	public static int toInt(Object obj)	{
		try	{
			if (obj == null || obj.toString().equals(""))
			{
				return 0;
			}
			else
			{
				return Integer.parseInt(obj.toString().trim());
			}
		}
		catch (Exception ex) {
			return 0;
		}
	}

	/**
	 * 将一个对象字串转化为一个数字
	 * @param obj Object
	 * @return int
	 */
	public static long toLong(Object obj)	{
		try	{
			if (obj == null || obj.toString().equals("")) {
				return 0L;
			}

			if(obj instanceof Long) {
				return Long.parseLong(obj.toString().trim());
			} else if(obj instanceof Integer) {
				return Integer.parseInt(obj.toString().trim());
			} else if(obj instanceof String) {
				return Long.parseLong(obj.toString().trim());
			}
		} catch (Exception ex) {
			return 0L;
		}
		return 0L;
	}

	/**
	 * 将一个对象字串转化为一个数字
	 * @param obj Object
	 * @return int
	 */
	public static double toDouble(Object obj)	{
		try	{
			if (obj == null || obj.toString().equals("")) {
				return 0.0;
			}

			if(obj instanceof Long) {
				return Long.parseLong(obj.toString().trim());
			} else if(obj instanceof Integer) {
				return Integer.parseInt(obj.toString().trim());
			} else if(obj instanceof Double) {
				return Double.parseDouble(obj.toString().trim());
			} else if(obj instanceof String) {
				return Double.parseDouble(obj.toString().trim());
			}
		} catch (Exception ex) {
			return 0.0;
		}
		return 0.0;
	}
	
	/**
	 * 按照format格式化显示日期时间
	 * @param source 日期的字符串
	 * @param format 日期的格式 
	 * @return
	 */
	public static String getDateString(Date source, String format)
	{
		if (source == null)	return "";
		try	{
			DateFormat formatter = new SimpleDateFormat(format);
			return formatter.format(source);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
     * 将字串转成日期和时间，
     * @param date
     * @param format 字串格式: yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date getDateString(String date,String format) {
        try {
        	if(isEmpty(date)) return null;
            DateFormat formatter = new SimpleDateFormat(format);
            return (Date) formatter.parse(date);
        } catch (Exception e) {
        	e.printStackTrace();
            return null;
        }
    }

	/**
	 * 得到给定日期的后 n 个月的日期
	 * 
	 * @param month
	 *            月份
	 * 
	 * @return
	 */
	public static String getDateAfterMonth(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

	}

	/**
	 * 产生给定位数的随机数
	 * 
	 * @param count
	 *            随机数的位数
	 * @return
	 */
	public static String getRandom(int count) {
		Random random = new Random();
		String strNum = "";
		while (true) {
			strNum = strNum + random.nextInt(10);
			if (strNum.length() == count)
				break;
		}
		return strNum;
	}

	/**
	 * 获取到指定范围内的随机数
	 * @param min 随机数最小范围
	 * @param max 随机数最大范围
	 * @return
	 */
	public static int getRangeRandom(int min, int max) {
		Random random = new Random();
		int randNumber =random.nextInt(max - min + 1) + min;
		return randNumber;
	}

	public static String encodeUrl(String encodeURL, String charsetEncode) {
		String url = "";
		if (CommUtils.isEmpty(encodeURL) || CommUtils.isEmpty(charsetEncode))
			return url;
		try {
			url = URLEncoder.encode(encodeURL, charsetEncode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	public static String encodeUrl(String encodeURL) {
		return encodeUrl(encodeURL, "utf-8");
	}

	public static String decodeUrl(String decodeURL, String charsetEncode) {
		String url = "";
		if (CommUtils.isEmpty(decodeURL) || CommUtils.isEmpty(charsetEncode))
			return url;
		try {
			url = URLDecoder.decode(decodeURL, charsetEncode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	public static String decodeUrl(String decodeURL) {
		return decodeUrl(decodeURL, "utf-8");
	}
	
	/**
	 * 
	 * @return
	 * @Description:获取web项目根目录
	 */
	public static String getWebRootPath(){
		String classPath=Thread.currentThread().getContextClassLoader().getResource("/").getPath();
		int webinf=classPath.indexOf("WEB-INF");
		return classPath.substring(0, webinf);
	}
	
	public static String escapeHtml(String str) {
		if (isEmpty(str))
			return "";
		str = str.trim();
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll(" ", "&nbsp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\'", "&#039;");
		str = str.replaceAll("\r\n", "<br/>");
		str = str.replaceAll("\n", "<br/>");
		str = str.replaceAll("\r", "<br/>");
		return str;
	}
	
	/**
	 * 判断此字符串(对象转化)是否为空、空字符串，或"null"
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(Object obj) {
		if (obj == null) return true;
		
		String szTmp = obj.toString();
		
		if (szTmp.equals("") || szTmp.equalsIgnoreCase("null"))
			return true;
		else
			return false;
	}

	public static boolean isNotNull(Object obj) {
		return !CommUtils.isNull(obj);
	}
	
    /**
     * 返回客户端IP地址
     * @param request HttpServletRequest
     * @return 客户端IP地址，若等于""，则表示取IP失败
     */
    public static String getRealRemoteIP(HttpServletRequest request){
		String ret = "";
		String cdnSrcIp = request.getHeader("Cdn-Src-Ip");
		if(cdnSrcIp == null || cdnSrcIp.length()<= 0){
			String ip = request.getHeader("X-forwarded-for");
			if (ip == null || ip.length()<= 0 || "unknoun".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (ip == null || ip.length()<= 0 || "unknoun".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length()<= 0 || "unknoun".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X_REAL_IP");
			}

			if (ip == null || ip.length()<= 0 || "unknoun".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr( );
			}

			if (ip!=null) {
				ret = ip;
			}
		}else {
			ret = cdnSrcIp.trim();
		}

		if(ret.startsWith("0:0:0:0:"))
		{
			ret = "127.0.0.1";
		}
		return ret;
    }
    
    /**
     * 获取密码的强、中、弱度
     * @param pwd 密码
	 * @update_author Hugo.kuang
	 * @update_time 2018-10-11
     * @return 0 弱 、 1 中、2 高 
     */
    public static int getPwdIntensiion(String pwd) {
		/**
		 * 密码强度评分：
		 密码大于等于6位数为20
		 密码出现大写字母分数为20
		 密码出现小写字母分数为15
		 密码出现数字分数为15
		 密码出现特殊符号分数为20

		 分数为50以下为低
		 50-80为中
		 80以上为高
		 */
		int score = 0;
		Pattern p1 = Pattern.compile("[a-z]+");
		Pattern p2 = Pattern.compile("[A-Z]+");
		Pattern p3 = Pattern.compile("\\d+");
		Pattern p4 = Pattern.compile("[!,@,#,$,%,^,&,*,?,_,~]+");

		Matcher m1 = p1.matcher(pwd);
		Matcher m2 = p2.matcher(pwd);
		Matcher m3 = p3.matcher(pwd);
		Matcher m4 = p4.matcher(pwd);

		if (pwd.length() > 2) {
			//长度大于6
			if (pwd.length() >= 6) {
				score += 20;
			}
			//有小写字母
			if (m1.find()) {
				score += 15;
			}
			// 有大写字母
			if (m2.find()) {
				score += 20;
			}
			// 有数字
			if (m3.find()) {
				score += 15;
			}
			// 有特殊字符
			if (m4.find()) {
				score += 20;
			}

		}

		if (score < 50) {
			return 0;
		} else if (score >= 50 && score <= 80) {
			return 1;
		} else if (score > 80) {
			return 2;
		}
		return 0;
	}

	/**
	 * 根据code获取对应中英说明
	 * @param code
	 * @param lang cn中文 en英文
	 * @return
	 */
	public static String getResponseMsg(int code,String lang) {
		for (BaseResponse br : BaseResponse.values()) {
			if(br.getResponseCode()==code) {
				if("cn".equalsIgnoreCase(lang)) {
					return br.getResponseMsgCn();
				} else {
					return br.getResponseMsgEn();
				}

			}
		}
		return "";
	}

	/**
	 * 根据索引获取
	 * @param <T>
	 * @param clazz
	 * @param index
	 * @return
	 */
	public static <T> T getEnum(Class<T> clazz,int index){
		T[] c=clazz.getEnumConstants();
		return c[index];
	}

	/**
	 * 根据name获取
	 * @param <T>
	 * @param enumType
	 * @param name
	 * @return
	 */
	public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
		return (T)Enum.valueOf(enumType, name);
	}

	/**
	 * 获取到多少天后的日期
	 * @return
	 */
	public static Date getDateAfterDays(int days) {
		LocalDateTime timePoint = LocalDateTime.now();
		System.out.println(timePoint);
		// 3 years, 2 months, 1 day
		Period period1 = Period.of(0, 0, days);
		timePoint = timePoint.plus(period1);

		ZoneId zone = ZoneId.systemDefault();
		Instant instant = timePoint.atZone(zone).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	//首字母转小写
	public static String toLowerCaseFirstOne(String s){
		if(isEmpty(s)) return s;
		if(Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	//首字母转大写
	public static String toUpperCaseFirstOne(String s){
		if(isEmpty(s)) return s;
		if(Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

}
