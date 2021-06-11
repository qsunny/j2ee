package com.aaron.base.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.sql.Timestamp;

/**
 * Json相关操作相关操作
 * @author aaron.qiu
 * @since 2021-2025
 */
public class JsonUtils {

	public static Object jsonToObj(String json, Class clazz) {
		return getGson().fromJson(json, clazz);
	}

	public static <T> T jsonToObject(String jsonStr, Class<T> clazz) {
		return getGson().fromJson(jsonStr,clazz);
	}

	public static <T> T jsonToObject(String jsonStr, Type listType) {
		return getGson().fromJson(jsonStr,listType);
	}

	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		gsonBuilder.disableHtmlEscaping();
		Gson GSON = gsonBuilder.create();
		return GSON;
	}

	public static String toJson(Object obj) {
		return getGson().toJson(obj);
	}
}