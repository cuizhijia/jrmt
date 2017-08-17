package com.swift.jrmt.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.swift.jrmt.common.enums.SafeLevel;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * 
 *         2015年5月19日 上午9:21:55
 */
public final class PassportSecurity {

	public static final String SECURITY = "sfXd!@#s$%^l&*n";
	public static final String WEB_SECURITY = "sf!@.#s$^%^l&@!Xd";

	public static String getRawPassword(long userId, String md5Password) {
		return Codings.MD5Encoding(userId + md5Password + userId);
	}

	/**
	 * 密码加密
	 * 
	 * @param pwd
	 * @return String
	 */
	public static String encryptAtWeb(String rawPassword) {
		return new StringBuilder(Codings.MD5Encoding(WEB_SECURITY + rawPassword)).reverse().toString();
	}

	/**
	 * 密码安全级别算法(未完成)
	 *  @see getSafeLevelNew
	 * @param password
	 * @return SafeLevel
	 */
	@Deprecated
	public static SafeLevel getSafeLevel(String password) {
		// Asserts.isTrue(password.length() >= 6);
		int hasDigit = 0;
		int hasLowChar = 0;
		int hasUpperChar = 0;
		int hasSpecialChar = 0;

		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (c >= (char) 'a' && c <= 'z' && hasLowChar == 0) {// 存在小写字母
				hasLowChar++;
			} else if (c >= (char) 'A' && c <= 'Z' && hasUpperChar == 0) {// 存在大写字母
				hasUpperChar++;
			} else if (c >= (char) '0' && c <= '9' && hasDigit == 0) {// 存在数字
				hasDigit++;
			} else if (("" + c).matches("[-`=\\;',./~!@#$%^&*()_+|{}:\"<>?]+") && hasSpecialChar == 0) {// 存在特殊字符
				hasSpecialChar++;
			}
		}

		int safeLevel = hasDigit + hasLowChar + hasUpperChar + hasSpecialChar;
		if (safeLevel <= 1) {
			return SafeLevel.LOW;
		} else if (safeLevel > 1 && safeLevel <= 3) {
			return SafeLevel.NORMAL;
		} else {
			return SafeLevel.HIGH;
		}
	}

	/**
	 * 获取密码安全级别
	 * @param password 密码
	 * @return 安全级别
	 */
	public static SafeLevel getSafeLevelNew(String password) {
		/*
		 * 0 密码为空 
		 * 1 1~6 纯数字、字母、特殊字符 
		 * 2 6~ 10 位 数字、字母、特殊字符两种及以上组合 
		 * 3 11~12位 数字、大写字母、小写字母及符号四种组合
		 */
		
		Pattern p1 = Pattern.compile("[a-zA-Z]");// 有字母
		Pattern p2 = Pattern.compile("[0-9]");// 有数字
		Pattern p3 = Pattern.compile("[\\W]");// 有特殊字符
		Pattern p4 = Pattern.compile("([0-9a-zA-Z\\W])\\1+");// 重复字母或数字或特殊字符

		Matcher m1 = p1.matcher(password);
		Matcher m2 = p2.matcher(password);
		Matcher m3 = p3.matcher(password);
		Matcher m4 = p4.matcher(password);
		
		boolean isSeq = isSequenceStr(password);
		int category = getCategoryOfChar(password);
		
		if (password == null || password.length() <= 0) {
			return SafeLevel.ERROR;
		}
		if (m4.matches() || isSeq || category <= 1){//重复连续纯
			return SafeLevel.LOW;
		}
		int level = 0;
		if (getCharNum(m1)>=2){//包含字母
			level ++;
		}
		if (getCharNum(m2)>=2){//包含数字
			level ++;
		}
		if (getCharNum(m3)>=2){//包含特殊字符
			level ++;
		}
		if(category>1 && category<=3 && level<=1){
            return SafeLevel.LOW;
        }
    	if(category>1 && category<=3 && level>=2){
    		return SafeLevel.NORMAL;//中等密码
    	}else if(category>3){
    		return SafeLevel.HIGH;//强密码
    	}
		return SafeLevel.ERROR;
	}
	
	private static int getCharNum(Matcher m){
		int c = 0;
    	while(m.find()){
    		c++;
    	}
    	return c;
	}
	
	/**
	 * 是否连续
	 * @param str 待校验密码
	 * @return 是否连续
	 */
	public static boolean isSequenceStr(String str) {
		//连号
		boolean isSeq=true;
		int pre=0;
		for(int i=0;i<str.length();i++){
			char d=str.charAt(i);
			if(i==0){
    			pre=d;
    			continue;
    		}
			if(d-1!=pre&&d+1!=pre){
				isSeq=false;
    		}
    		pre = d;
		}
		return isSeq;
	}
	
	/**
	 * 获取组合数量
	 * @param str 待校验字符串
	 * @return 组合数量
	 */
	public static int getCategoryOfChar(String str){
		int level=0;
        Pattern p1 = Pattern.compile("[a-zA-Z]{1,}");//有字�?
    	Pattern p2 = Pattern.compile("[0-9]{1,}");//有数�?
    	Pattern p3 = Pattern.compile("[\\W]{1,}");//有特殊字�?
    
		Matcher matcher1 = p1.matcher(str);
		Matcher matcher2= p2.matcher(str);
		Matcher matcher3 = p3.matcher(str);
		if(matcher1.find()){//包含字母
			level++;
		}
		if(matcher2.find()){//包含数字
			level++;
		}
		if(matcher3.find()){//包含特殊字符
			level++;
		}
		return level;
	}

	/*
	 * 
	 * var checkLv_2 = function (e) { var val = this.value; //纯数字，纯字母，纯特殊字符 var
	 * weakReg = /^(?:\d+|[a-zA-Z]+|[^a-zA-Z0-9]+)$/g;
	 * //数字+字母，数字+特殊字符，字母+特殊字符，而且不能是纯数字，纯字母，纯特殊字符 及 数字+字母+特殊字符组合 //todo:
	 * 三种组合暂时不能过滤 var mediumReg =
	 * /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).+$/g;
	 * //数字+字母+特殊字符，而且不能是纯数字，纯字母，纯特殊字符 及 任意两两组合 var strongReg =
	 * /^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$)(?![^a-zA-Z]+$)(?![^\d]+$)(?![
	 * \da-zA-Z]+$).+$/g;
	 * //^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[~!@#$%^&*._])[\w~!@#$%^&*.]+$
	 * if(weakReg.test(val)) { //弱 } else if(mediumReg.test(val)) { //中 } else
	 * if(strongReg.test(val)) { //强 } };
	 */
}
