package com.aaron.base.utils;

import java.net.InetAddress;

/**
 * UUID相关操作
 * @author aaron.qiu
 * @since 2015-2016
 */
public class UUIDGenerator {
	private static final int IP;
	private static short counter;
	private static final int JVM;

	public static String generate() {
//		return 32 + format(getIP()) + format(getJVM()) + format(getHiTime())
		return format(getIP()) + format(getJVM()) + format(getHiTime())
				+ format(getLoTime()) + format(getCount());
	}

	private static final String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuilder buf = new StringBuilder("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	private static final String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuilder buf = new StringBuilder("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	private static final int getJVM() {
		return JVM;
	}

	private static final short getCount() {
		synchronized (UUIDGenerator.class) {
			if (counter < 0)
				counter = 0;
			return counter++;
		}
	}

	private static final int getIP() {
		return IP;
	}

	private static final short getHiTime() {
		return (short) (int) (System.currentTimeMillis() >>> 32);
	}

	private static final int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	private static final int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - -128 + bytes[i];
		}
		return result;
	}

	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;

		counter = 0;

		JVM = (int) (System.currentTimeMillis() >>> 8);
	}
}
