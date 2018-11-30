package com.chat.controller;

import spark.ResponseTransformer;

import com.chat.model.Account;
import com.google.gson.Gson;

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

	public static Account fromPlayerJson(final String json, Class<Account> user) {
		return GSON.fromJson(json, user);
	}

	public static Account fromPlayerStatusJson(final String json, Class<Account> user) {
		return GSON.fromJson(json, user);
	}
}
