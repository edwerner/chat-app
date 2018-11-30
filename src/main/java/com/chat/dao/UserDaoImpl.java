package com.chat.dao;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chat.controller.JsonUtils;
import com.chat.model.Account;
import com.chat.model.User;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserDaoImpl implements UserDao {
	private final String PLAYER_FILE_LOCATION = "database/players.txt";

	public UserDaoImpl() throws IOException {
		File playerFile = new File(PLAYER_FILE_LOCATION);
		playerFile.createNewFile();
	}

	@Override
	public void savePlayer(User player) {
		try {
			JsonObject playerObject = new JsonObject();
			JsonObject attributesObject = new JsonObject();
			attributesObject.addProperty("username", player.getUsername());
			attributesObject.addProperty("password", player.getPassword());
			playerObject.add("user", attributesObject);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(PLAYER_FILE_LOCATION, true));
			outputStream.write(JsonUtils.toJson(playerObject));
			outputStream.newLine();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User findPlayerByUsername(String username) {
		Account player = null;
		Account existingPlayer = null;
		JsonObject parserObject;
		String fileName = PLAYER_FILE_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = (JsonObject) new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject("player");
				String json = JsonUtils.toJson(playerObject);
				player = JsonUtils.fromPlayerJson(json, Account.class);
				if (player != null) {
					if (player.getUsername().equals(username)) {
						existingPlayer = player;
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return existingPlayer;
	}

	@Override
	public boolean passwordsMatch(User player) {
		Account existingPlayer = null;
		boolean passwordsMatch = false;
		JsonObject parserObject;
		String line = null;

		try {
			FileReader fileReader = new FileReader(PLAYER_FILE_LOCATION);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			Pattern p = Pattern.compile(Pattern.quote(player.getPassword())); // quotes in case you need 'Hello.'
			@SuppressWarnings("unused")
			int count = 0;
			while ((line = bufferedReader.readLine()) != null)
				for (Matcher m = p.matcher(line); m.find(); count++) {
					parserObject = (JsonObject) new JsonParser().parse(line);
					JsonObject playerObject = parserObject.getAsJsonObject("player");
					String json = JsonUtils.toJson(playerObject);
					existingPlayer = JsonUtils.fromPlayerJson(json, Account.class);
					if (existingPlayer != null) {
						if (existingPlayer.getUsername().equals(player.getUsername())
								&& existingPlayer.getPassword().equals(player.getPassword())) {
							passwordsMatch = true;
						} else {
							passwordsMatch = false;
						}
					}
				}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return passwordsMatch;
	}
}
