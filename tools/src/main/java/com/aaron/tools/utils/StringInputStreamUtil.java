package com.aaron.tools.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @author aaron.qiu
 * @since 2015-2016
 */
public class StringInputStreamUtil {
	public static InputStream convertStringToStream2(String str,
			String charsetName) throws UnsupportedEncodingException {
		return new ByteArrayInputStream(str.getBytes(charsetName));
	}

	public static InputStream convertStringToStream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}

	public static String inputStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null)
				sb.append(new StringBuilder().append(line).append("/n")
						.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String inputStreamToString1(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null)
				sb.append(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	public static String inputStreamToString2(InputStream in)
			throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		int n;
		while ((n = in.read(b)) != -1) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	public static String inputStreamToString3(InputStream is)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
}