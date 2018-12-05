package com.chat.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.chat.controller.JsonUtils;
import com.chat.model.Message;
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
//			JsonObject playerObject = new JsonObject();
			JsonObject attributesObject = new JsonObject();
			attributesObject.addProperty("text", message.getMessage());
			attributesObject.addProperty("username", message.getUsername());
			attributesObject.addProperty("id", message.getId());
			attributesObject.addProperty("removed", message.getRemoved());
//			playerObject.add("message", attributesObject);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(MESSAGE_FILE_LOCATION, true));
			outputStream.write(JsonUtils.toJson(attributesObject));
			outputStream.newLine();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveMessageById(String messageId) {
		Message message = null;
		String line = null;
		try {
			FileReader fileReader = new FileReader(MESSAGE_FILE_LOCATION);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				JsonObject parserObject = (JsonObject) new JsonParser().parse(line);
				JsonObject messageObject = parserObject.getAsJsonObject();
				String json = JsonUtils.toJson(messageObject);
				message = JsonUtils.fromMessageJson(json, Message.class);
				
				if (message.getId().equals(messageId)) {
					message.setMessage("Post has been removed by Admin");
					message.setRemoved();
					String jsonUpdated = JsonUtils.toJson(message);
					Path PATH = Paths.get(MESSAGE_FILE_LOCATION);
					List<String> fileContent = new ArrayList<>(Files.readAllLines(PATH, StandardCharsets.UTF_8));

					for (int i = 0; i < fileContent.size(); i++) {
					    if (fileContent.get(i).equals(json)) {
					    	System.out.println("FOUND A MATCH");
					        fileContent.set(i, jsonUpdated);
					        break;
					    }
					}
					Files.write(PATH, fileContent, StandardCharsets.UTF_8);
					break;
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
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
				JsonObject messageObject = parserObject.getAsJsonObject();
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
