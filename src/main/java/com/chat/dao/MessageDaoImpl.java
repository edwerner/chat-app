package com.chat.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.chat.controller.JsonUtils;
import com.chat.model.Message;
import com.google.gson.JsonObject;

public class MessageDaoImpl implements MessageDao {
	private final String MESSAGE_FILE_LOCATION = "database/messages.txt";

	public MessageDaoImpl() throws IOException {
		File playerFile = new File(MESSAGE_FILE_LOCATION);
		playerFile.createNewFile();
	} 

	@Override
	public void saveMessage(Message message) {
		try {
			JsonObject playerObject = new JsonObject();
			JsonObject attributesObject = new JsonObject();
			attributesObject.addProperty("message", message.getMessage());
			playerObject.add("user", attributesObject);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(MESSAGE_FILE_LOCATION, true));
			outputStream.write(JsonUtils.toJson(playerObject));
			outputStream.newLine();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
