package com.swift.jrmt.common.utils;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * 
 * 2015年5月10日 下午8:25:44
 */
public abstract class PWDEncoder {

	/**
	 * 
	 * @param rawPass
	 * @param sugar
	 * @return
	 * String
	 * 
	 * @see com.yifangming.person.PassportService#encodePassword(Long objectId, String rawPass, String sugar)
	 */
	public static String encodePassword(String rawPass, String sugar){
		rawPass = reverse(Codings.MD5Encoding(sugar + rawPass));
		return BCrypt.hashpw(rawPass, BCrypt.gensalt(12));
	}


	/**
	 * 
	 * @param rawPass
	 * @param encPass
	 * @param sugar
	 * @return
	 * boolean
	 * 
	 * @see com.yifangming.person.PassportService#check(Long objectId, String rawPass, String encPass)
	 */
	public static boolean check(String rawPass, String encPass, String sugar) {
		rawPass = reverse(Codings.MD5Encoding(sugar + rawPass));
		return BCrypt.checkpw(rawPass, encPass);
	}

	/**
	 * 反转字符串
	 * @param p
	 * @return
	 * String
	 */
	public static String reverse(String p){
		return new StringBuffer(p).reverse().toString();
	}


	public static void main(String[] args) {
		//String hashed1 = encodePassword("123456", "howsun");
		//String hashed2 = encodePassword("123456", "howsun");
		//System.out.println(hashed1);
		//System.out.println(hashed2);
		String hashed = "$2a$12$FhXRITVLrb.1qzzVW87H9u4tI4r0bY2InHoh0uelRISH1VY3/ax9.";
		//System.out.println(hashed);
		System.out.println(check("23456", hashed, "howsun"));
		System.out.println(check("12345", hashed, "howsun"));
		System.out.println(check("123456", hashed, "howsun"));
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			String rand = Randoms.nextString(5);
			String h = encodePassword(rand, "howsun");
			System.out.println(h + "\t" + check(rand, h, "howsun"));
		}
		System.out.println(System.currentTimeMillis() - start);
	}
}
