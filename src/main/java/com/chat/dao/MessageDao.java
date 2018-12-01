package com.chat.dao;

import java.util.ArrayList;

import com.chat.model.Message;

public interface MessageDao {
	void saveMessage(Message message);
	ArrayList<Message> getMessages();
}
