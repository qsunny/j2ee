package com.aaron.tools.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Ip相关操作
 * @author aaron.qiu
 * @since 2015-2016
 */
public class IpUtils {
	  
	public static String[] getAllLocalHostIP() {
	    List<String> res = new ArrayList<String>();
	    try
	    {
	      Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
	      InetAddress ip = null;
	      while (netInterfaces.hasMoreElements()) {
	        NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();

	        Enumeration nii = ni.getInetAddresses();
	        while (nii.hasMoreElements()) {
	          ip = (InetAddress)nii.nextElement();
	          if (ip.getHostAddress().indexOf(":") == -1)
	            res.add(ip.getHostAddress());
	        }
	      }
	    }
	    catch (SocketException e) {
	      e.printStackTrace();
	    }
	    return (String[])(String[])res.toArray(new String[0]);
	  }
	
	public static String getMacAddr() {
		String MacAddr = "";
		String str = "";
		try {
			NetworkInterface NIC = NetworkInterface.getByName("eth0");
			byte[] buf = NIC.getHardwareAddress();
			for (int i = 0; i < buf.length; i++) {
				str = str + byteHEX(buf[i]);
			}
			MacAddr = str.toUpperCase();
		} catch (SocketException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return MacAddr;
	}

	public static String getMacAddress() {
		String mac = "";
		String line = "";

		String os = System.getProperty("os.name");

		if ((os != null) && (os.startsWith("Windows"))) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);

				BufferedReader br = new BufferedReader(new InputStreamReader(
						p.getInputStream()));

				while ((line = br.readLine()) != null) {
					if (line.indexOf("Physical Address") > 0) {
						int index = line.indexOf(":") + 2;

						mac = line.substring(index);
					}

				}

				br.close();
			} catch (IOException e) {
			}
		}
		return mac;
	}

	public static String getLocalIP() {
		Enumeration allNetInterfaces = null;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		InetAddress ip = null;
		while (allNetInterfaces.hasMoreElements()) {
			NetworkInterface netInterface = (NetworkInterface) allNetInterfaces
					.nextElement();

			Enumeration addresses = netInterface.getInetAddresses();
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if ((ip != null) && ((ip instanceof Inet4Address))) {
					String ipLocal = ip.getHostAddress();
					if (!ipLocal.equals("127.0.0.1")) {
						return ipLocal;
					}
				}
			}
		}
		return null;
	}

	public static String byteHEX(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
				'b', 'c', 'd', 'e', 'f' };

		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4 & 0xF)];
		ob[1] = Digit[(ib & 0xF)];
		String s = new String(ob);
		return s;
	}

	public static void main(String[] args) {
		System.out.println(getLocalIP());
	}
}