package com.chat.model;

public class Admin implements Account {

	private String username;
	private String password;
	private String accountType;

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getAccountType() {
		return accountType;
	}

	@Override
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}