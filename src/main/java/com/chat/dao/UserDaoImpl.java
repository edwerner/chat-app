package com.chat.dao;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chat.model.User;
import com.chat.controller.JsonUtils;
import com.chat.model.Account;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserDaoImpl implements UserDao {
	private final String USER_FILE_LOCATION = "database/users.txt";

	/**
	 * Constructor instantiates
	 * new user file to persist
	 * records
	 * 
	 * @throws IOException
	 */
	public UserDaoImpl() throws IOException {
		File userFile = new File(USER_FILE_LOCATION);
		userFile.createNewFile();
	}
	
	/**
	 * Uses file reader and buffered
	 * reader to read file lines,
	 * convert to json object and
	 * JSON string then overwrites
	 * and re-saves data file
	 *
	 * @param user
	 */
	@Override
	public void saveUser(Account user) {
		try {
			JsonObject playerObject = new JsonObject();
			JsonObject attributesObject = new JsonObject();
			attributesObject.addProperty("username", user.getUsername());
			attributesObject.addProperty("password", user.getPassword());
			attributesObject.addProperty("accountType", user.getAccountType());
			playerObject.add("user", attributesObject);
			BufferedWriter outputStream = new BufferedWriter(new FileWriter(USER_FILE_LOCATION, true));
			outputStream.write(JsonUtils.toJson(playerObject));
			outputStream.newLine();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Uses file reader and buffered
	 * reader to read file lines,
	 * convert to json object and
	 * JSON string then return domain
	 * model matching on username
	 *
	 * @param username
	 * @return user
	 */
	@Override
	public Account findUserByUsername(String username) {
		User user = null;
		User existingUser = null;
		JsonObject parserObject;
		String fileName = USER_FILE_LOCATION;
		String line = null;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				parserObject = (JsonObject) new JsonParser().parse(line);
				JsonObject playerObject = parserObject.getAsJsonObject("user");
				String json = JsonUtils.toJson(playerObject);
				user = JsonUtils.fromPlayerJson(json, User.class);
				if (user != null) {
					if (user.getUsername().equals(username)) {
						existingUser = user;
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return existingUser;
	}

	/**
	 * Uses file reader and buffered
	 * reader to read file lines,
	 * convert to json object and
	 * JSON string then return domain
	 * model matching on username
	 * and password
	 *
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean passwordsMatch(Account user) {
		User existingUser = null;
		boolean passwordsMatch = false;
		JsonObject parserObject;
		String line = null;

		try {
			FileReader fileReader = new FileReader(USER_FILE_LOCATION);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			Pattern p = Pattern.compile(Pattern.quote(user.getPassword())); // quotes in case you need 'Hello.'
			@SuppressWarnings("unused")
			int count = 0;
			while ((line = bufferedReader.readLine()) != null)
				for (Matcher m = p.matcher(line); m.find(); count++) {
					parserObject = (JsonObject) new JsonParser().parse(line);
					JsonObject playerObject = parserObject.getAsJsonObject("user");
					String json = JsonUtils.toJson(playerObject);
					existingUser = JsonUtils.fromPlayerJson(json, User.class);
					if (existingUser != null) {
						if (existingUser.getUsername().equals(user.getUsername())
								&& existingUser.getPassword().equals(user.getPassword())) {
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
