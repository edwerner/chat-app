package com.chat.model;

import java.util.UUID;

public class Message {

	private String text;
	private String username;
	private String id;
	private boolean removed;
	
	public Message() {
		this.id = UUID.randomUUID().toString();
		this.removed = false;
	}
	
	public String getId() {
		return id;
	}

	public String getMessage() {
		return text;
	}
	
	public void setMessage(String text) {
		this.text = text;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setRemoved() {
		this.removed = true;
	}
	
	public boolean getRemoved() {
		return this.removed;
	}
}