package com.swift.jrmt.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * 各种格式的编码加码工具类
 * @Date 2010-10-26
 * @version v0.1
 */
public abstract class Codings {

	public static final String GBK = "GBK";
	public static final String UTF8 = "UTF-8";
	public static final String UTF16 = "UTF-16";
	public static final String UTF16BE = "UTF-16BE";
	public static final String UTF16LE = "UTF-16LE";
	public static final String US_ASCII = "US-ASCII";
	public static final String ISO8859_1 = "ISO-8859-1";
	public static final Charset CHARSET_GBK = Charset.forName(GBK);
	public static final Charset CHARSET_UTF8 = Charset.forName(UTF8);
	public static final Charset CHARSET_ISO8859_1 = Charset.forName(ISO8859_1);

	/**
	 * 默认编码
	 * @return String
	 */
	public static String getDefaultEncoding(){
		return Charset.defaultCharset().name();
	}

	/**
	 * MD5加密
	 * @param origin
	 * @return 加密后的结果
	 */
	public static String MD5Encoding(String origin){
		return MD5.MD5Encoding(origin);
	}

	/**
	 * Base64编码
	 * @param input
	 * @return
	 */
	public static String base64Encode(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}
	
	public static byte[] base64EncodeToBytes(byte[] input) {
		return Base64.encodeBase64(input);
	}
	 
	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
	 * @param input
	 * @return
	 */
	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}
	 
	/**
	 * Base64解码.
	 * @param input
	 * @return
	 */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}
	 
	/**
	 * URL 编码, Encode默认为UTF-8. 
	 * @param input
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlEncode(String input) throws UnsupportedEncodingException {
			return URLEncoder.encode(input, UTF8);
	}
	 
	/**
	 * URL 解码, Encode默认为UTF-8. 
	 * @param input
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlDecode(String input) throws UnsupportedEncodingException {
			return input==null?null:URLDecoder.decode(input, UTF8);
	}
	 

	/**
	 * MD5加密算法
	 */
	static class MD5{

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


}
