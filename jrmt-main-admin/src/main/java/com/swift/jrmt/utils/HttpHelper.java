package com.swift.jrmt.utils;

import javax.servlet.http.HttpServletRequest;


public abstract class HttpHelper {

	private static String AUTHED_USER_KEY = "__AUTHED_USER__";
	
	public static Object getAuthUser(HttpServletRequest request){
		return  request.getAttribute(AUTHED_USER_KEY);
	}
	
	public static void setAuthUser(HttpServletRequest request, Object user){
		request.setAttribute(AUTHED_USER_KEY, user);
	}
}
