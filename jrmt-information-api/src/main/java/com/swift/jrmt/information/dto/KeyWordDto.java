package com.swift.jrmt.information.dto;

import com.swift.jrmt.information.model.KeyWord;

public class KeyWordDto extends KeyWord {

	private static final long serialVersionUID = 5863477488980980645L;
	
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
