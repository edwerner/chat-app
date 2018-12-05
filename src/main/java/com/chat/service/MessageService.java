package com.chat.service;

import java.io.IOException;
import java.util.ArrayList;

import com.chat.dao.MessageDaoImpl;
import com.chat.model.Message;

public class MessageService {

	private MessageDaoImpl messageDaoImpl;

	/**
	 * Constructor instantiates
	 * new messag dao implementation
	 * 
	 * @throws IOException
	 */
	public MessageService() throws IOException {
		messageDaoImpl = new MessageDaoImpl();
	}

	public void saveMessage(Message message) {
		messageDaoImpl.saveMessage(message);
	}
	
	public ArrayList<Message> getMessages() {
		return messageDaoImpl.getMessages();
	}
}
