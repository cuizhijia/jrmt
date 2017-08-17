package com.swift.jrmt.common.utils;

import java.security.MessageDigest;


/**
 * 
 * 说明:MD5加密
 * 
 * 调用：MD5Encoding(origin);
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * 
 * 2015年5月12日 上午11:30:12
 */
public class MD5 {

	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	private static MessageDigest md; 
	static {
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (Exception e) {}
	};
	public static String byteArrayToHexString(byte[] b) { 
		StringBuffer resultSb = new StringBuffer();

		for (int i = 0; i < b.length; ++i) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString(); 
	}

	private static String byteToHexString(byte b){
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encoding(String origin) {
		String resultString = null;
		try{
			resultString = new String(origin);
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		}
		catch (Exception localException) {
		}
		return resultString;
	}
}
