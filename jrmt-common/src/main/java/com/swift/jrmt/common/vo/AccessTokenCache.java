package com.swift.jrmt.common.vo;

import java.io.Serializable;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * 
 * 2015年5月13日 下午5:31:53
 */
public class AccessTokenCache implements Serializable {

	
	private static final long serialVersionUID = 6692118517812086965L;

	private String accessToken;
	
	private long expiers;

	
	public AccessTokenCache() {
		super();
	}
	
	public AccessTokenCache(String accessToken, long expiers) {
		super();
		this.accessToken = accessToken;
		this.expiers = expiers;
	}



	//-----------------------------------------------------------------
	
	

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getExpiers() {
		return expiers;
	}

	public void setExpiers(long expiers) {
		this.expiers = expiers;
	}
	
	
}
