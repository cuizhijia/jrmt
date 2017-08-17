package com.swift.jrmt.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Web层工具
 *
 * @author 张纪豪
 * @Date 2007-4-26
 * @version v1.0
 */
public class Webs {

	public static final String URL_PATTERN = "(http://)?([^/]*)(/?.*)";

	public enum UrlCodeType{
		BASE64,
		URLENCODE;
	}

	
	/**
	 * 从Request对象中取出字符串
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getStringByRequestParameter(HttpServletRequest request, String name, String defaultValue){
		String str = request.getParameter(name);
		return str == null ?  defaultValue : str.trim();
	}

	
	/**
	 * 从Request对象中取出布尔值
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static boolean getBooleanByRequestParameter(HttpServletRequest request, String name, boolean defaultValue){
		String value = request.getParameter(name);
		return value == null ? defaultValue : Boolean.parseBoolean(request.getParameter(name));
	}

	
	/**
	 * 从Request对象中取出整数
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static Integer getIntByRequestParameter(HttpServletRequest request, String name, Integer defaultValue){
		String str = request.getParameter(name);
		if(str == null) return defaultValue;
		try {
			return Integer.parseInt(str);
		} 
		catch (Exception e) {
			return defaultValue;
		}
	}
	
	
	/**
	 * 从Request对象中取出Long整数
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static Long getLongByRequestParameter(HttpServletRequest request, String name, Long defaultValue){
		String str = request.getParameter(name);
		if(str == null){
			return defaultValue;
		}
		
		try {
			return Long.parseLong(str);
		} 
		catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 从Request对象中取出Double
	 * @param request
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static Double getDoubleByRequestParameter(HttpServletRequest request, String name, Double defaultValue){
		String str = request.getParameter(name);
		if(str == null){
			return defaultValue;
		}
		
		try {
			return Double.valueOf(str);
		} 
		catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 *
	 * @param request
	 * @param isSyncBase64Encoder
	 * @Deprecated
	 * @see Webs#getUrl(HttpServletRequest, UrlCodeType)
	 * @return
	 */

	public static String getUrl(HttpServletRequest request, boolean isSyncBase64Encoder){
		String forward = (String) request.getAttribute("javax.servlet.forward.servlet_path");
		StringBuffer url = new StringBuffer(forward == null ? request.getRequestURL() : forward);
		String parm = param(request);
		if (StringUtil.hasLength(parm)) {
			url.append("?").append(parm);
		}

		return isSyncBase64Encoder ? Codings.base64Encode(url.toString().getBytes()) : url.toString();

		//new String(new BASE64Encoder().encode(url.toString().getBytes()));
	}

	/**
	 *
	 * @param request
	 * @param urlCodeType
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String getUrl(HttpServletRequest request, UrlCodeType urlCodeType) throws UnsupportedEncodingException{
		String forward = (String) request.getAttribute("javax.servlet.forward.servlet_path");
		StringBuffer url = new StringBuffer(forward == null ? request.getRequestURL() : forward);
		String parm = param(request);
		if (StringUtil.hasLength(parm)) {
			url.append("?").append(parm);
		}
		String result = url.toString();
		if(urlCodeType != null)
		switch (urlCodeType) {
		case BASE64:
			return Codings.base64Encode(result.getBytes());
		case URLENCODE:
			return URLEncoder.encode(result, "UTF-8");

		}
		return result;
	}

	/**
	 * 解码url字符串
	 * @param url
	 * @param urlCodeType 编码方式
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String parseUrl(String url, UrlCodeType urlCodeType) throws UnsupportedEncodingException{
		if(urlCodeType != null){
			switch (urlCodeType) {
			case BASE64:
				return new String(Codings.base64Decode(url), "UTF-8");
			case URLENCODE:
				return URLDecoder.decode(url, "UTF-8");
			}
		}
		return url;
	}


	/**
	 * 获取URL上的参数，但可以忽略指定的参数
	 * @param request
	 * @param ignores
	 * @return
	 */
	public static String param(HttpServletRequest request, String...ignoreParams){

		StringBuffer url = new StringBuffer();
		Enumeration<?> param = request.getParameterNames();//得到所有参数名

		Set<String> ignoreSet = ignoreParams.length == 0 ? null : new HashSet<String>(Arrays.asList(ignoreParams));

		//如果没有忽略参数，则单独处理以提高性能
		if(ignoreSet == null){
			while(param.hasMoreElements()){
				String pname = param.nextElement().toString();
				url.append(pname).append("=").append(request.getParameter(pname)).append("&");
			}
		}

		else{
			while(param.hasMoreElements()){
				String pname = param.nextElement().toString();
				if(ignoreSet.contains(pname)){
					continue;
				}
				url.append(pname).append("=").append(request.getParameter(pname)).append("&");
			}
		}

		if(url.toString().endsWith("&")){
			url.deleteCharAt( url.length() - 1 );
		}
		String result = url.toString().replace("\"", "%22").replace("'", "%27").replace("<", "&lt;").replace(">", "&gt;");
		return result;
	}

	

	public static String getValidateCode(Object scope){

		if(scope instanceof HttpSession){
			HttpSession session = (HttpSession) scope;
			return (String)session.getAttribute("validateCode");
		}

		if(scope instanceof HttpServletRequest){
			HttpServletRequest request = (HttpServletRequest) scope;
			return getValidateCode(request.getSession());
		}
		return null;
	}
	
	
	/**
	 * 检查验证码
	 * @param scope 作用域
	 * @param validateCode  用户输入的验证码
	 * @return
	 */
	public static Boolean isValidateCode(Object scope, String validateCode){
		return validateCode != null && validateCode.equalsIgnoreCase(getValidateCode(scope));

		/*if(scope instanceof HttpSession){
			HttpSession session = (HttpSession) scope;
			String code = (String)session.getAttribute("validateCode");
			return code == null ? false : code.equalsIgnoreCase(validateCode);
		}

		if(scope instanceof HttpServletRequest){
			HttpServletRequest request = (HttpServletRequest) scope;
			return isValidateCode(request.getSession(),validateCode);
		}
		return false;*/
	}

	
	/**
	 * 防止站外连接
	 * @param request
	 * @return
	 */
	public static boolean prohibitOutsideLinking(HttpServletRequest request){
		String Referer = "";
		boolean referer_sign = true;  //true 站内提交，验证通过  //false  站外提交，验证失败
		Enumeration<?> headerValues =  request.getHeaders("Referer");
		while (headerValues.hasMoreElements())
			Referer = (String)headerValues.nextElement();

		//判断是否存在请求页面
		if(Referer == null || Referer.length() < 1 ){
			referer_sign = false;
		}
		else {
			//判断请求页面和getRequestURI是否相同
			String servername_str = request.getServerName();
			if(StringUtil.hasLengthBytrim(servername_str)){
				int index = 0;
				if (StringUtil.indexOf(Referer, "https://")==0){
					index = 8;
				}
				else if (StringUtil.indexOf(Referer, "http://")==0){
					index = 7;
				}
				if(Referer.length() - index < servername_str.length())  //长度不够
					referer_sign = false;
				else{   //比较字符串（主机名称）是否相同
					String referer_str = Referer.substring(index,index + servername_str.length());
					if(!servername_str.equalsIgnoreCase(referer_str))
						referer_sign = false;
				}
			}
			else
				referer_sign = false;
		}
		return referer_sign;
	}

}
