package com.chat.controller;

import java.io.IOException;

import com.chat.dao.MessageDaoImpl;

import spark.Request;
import spark.Response;
import spark.Route;

public class PostRemoveMessageController {
	
	MessageDaoImpl messageDaoImpl;

	public PostRemoveMessageController() {
		try {
			messageDaoImpl = new MessageDaoImpl();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Remove message admin route
	 * edits message and re-saves
	 * with admin message overwrite
	 * 
	 * @return
	 */
	public Route postRemoveMessage() {
		return new Route() {
			@Override
			public Object handle(Request request, Response response) throws Exception {
				String messageId = request.queryParams("id");
				messageDaoImpl.editMessage(messageId);
				return messageId;
			}
		};
	}
}