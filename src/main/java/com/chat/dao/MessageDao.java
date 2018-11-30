package com.chat.dao;

import com.chat.model.Message;

public interface MessageDao {
	void saveMessage(Message message);
}
