package com.auth0.jwt;

/**
 * Sample object for serialization
 */
public class User {

	private String username;
	private String password;
	private Long delay;//最后有效期时间
	
	public User() {
	}
	
	public Long getDelay() {
		return delay;
	}


	public void setDelay(Long delay) {
		this.delay = delay;
	}


	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
