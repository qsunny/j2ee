package com.aaron.tools.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * base64相关操作
 * 
 * @author aaron.qiu
 * @since 2015-2016
 */
public class Base64Utils {

	public static String encodeStr(String plainText)
			throws UnsupportedEncodingException {
		byte[] b = plainText.getBytes("UTF-8");
		Base64 base64 = new Base64();
		b = base64.encode(b);
		String s = new String(b);
		return s;
	}

	public static String decodeStr(String encodeStr) {
		byte[] b = encodeStr.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		String s = new String(b);
		return s;
	}

}
