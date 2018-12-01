package com.chat.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.chat.controller.JsonUtils;
import com.chat.model.Account;
import com.chat.model.Message;
import com.chat.model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
			attributesObject.addProperty("username", message.getMessage());
			playerObject.add("message", attributesObject);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(MESSAGE_FILE_LOCATION, true));
			outputStream.write(JsonUtils.toJson(playerObject));
			outputStream.newLine();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("null")
	@Override
	public ArrayList<Message> getMessages() {
		Message message = null;
		ArrayList<Message> messages = new ArrayList<Message>();
		JsonObject parserObject;
		String fileName = MESSAGE_FILE_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = (JsonObject) new JsonParser().parse(line);
				JsonObject messageObject = parserObject.getAsJsonObject("message");
				String json = JsonUtils.toJson(messageObject);
				message = JsonUtils.fromMessageJson(json, Message.class);
				if (message != null) {
					messages.add(message);
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return messages;
	}
}