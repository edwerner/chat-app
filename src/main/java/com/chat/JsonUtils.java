package com.chat;

import spark.ResponseTransformer;

import com.chat.model.Message;
import com.chat.model.User;
import com.google.gson.Gson;

/**
 * JsonUtils class provides
 * different methods for
 * transforming from domain
 * models to json strings
 * and the inverse
 * 
 */
public class JsonUtils {

	private static Gson GSON = new Gson();

	public static <T> T fromJson(final String json, final Class<T> clazz) {
		return GSON.fromJson(json, clazz);
	}

	public static String toJson(Object object) {
		return GSON.toJson(object);
	}

	public static ResponseTransformer json() {
		return JsonUtils::toJson;
	}

	public static User fromPlayerJson(final String json, Class<User> user) {
		return GSON.fromJson(json, user);
	}

	public static Message fromMessageJson(final String json, Class<Message> message) {
		return GSON.fromJson(json, message);
	}
}
