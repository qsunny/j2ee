package com.aaron.springbootDemo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

/**
 * Json工具类
 * 
 */
public class JsonUtil {
	private static Gson gson;

	private JsonUtil() {

	}

	static {
		if (gson == null) {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
			gsonBuilder.disableHtmlEscaping();
			gson = gsonBuilder.create();
		}
	}
	
	public static Gson getInstance() {
        return gson;
    }

	public static String toJson(Object object) {
		return gson.toJson(object);
	}

	public static <T> T fromJson(String json, Class<T> cls) {
		T t = null;
		if (gson != null) {
			t = gson.fromJson(json, cls);
		}
		return t;
	}

	public static <T> List<T> json2List(String json, Class<T> cls) {
		List<T> list = null;
		if (gson != null) {
//			Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
            	if(list == null){
            		list = new ArrayList<T>();
            	}
                list.add(gson.fromJson(jsonElement, cls));  
            } 
		}
		return list;
	}

	public static <T> List<Map<String, T>> json2MapList(String json) {
		List<Map<String, T>> list = null;
		if (gson != null) {
			list = gson.fromJson(json, new TypeToken<List<Map<String, T>>>() {
			}.getType());
		}
		return list;
	}

	public static <T> Map<String, T> json2Map(String json) {
		Map<String, T> map = null;
		if (gson != null) {
			map = gson.fromJson(json, new TypeToken<Map<String, T>>() {
			}.getType());
		}
		return map;
	}
	
}
