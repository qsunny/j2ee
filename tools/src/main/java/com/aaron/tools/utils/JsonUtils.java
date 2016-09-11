package com.aaron.tools.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Json相关操作相关操作
 * @author aaron.qiu
 * @since 2015-2016
 */
public class JsonUtils {
	public static Map<String, Object> getMap(String jsonString) {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject = JSONObject.fromObject(jsonString);

			Iterator keyIter = jsonObject.keys();

			Map valueMap = new HashMap();
			while (keyIter.hasNext()) {
				String key = (String) keyIter.next();
				Object value = jsonObject.get(key);
				valueMap.put(key, value);
			}
			return valueMap;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object jsonToObj(String json, Class clazz) {
		return getGson().fromJson(json, clazz);
	}

	public static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		gsonBuilder.registerTypeAdapter(Timestamp.class,
				new TimestampTypeAdapter());
		Gson GSON = gsonBuilder.create();
		return GSON;
	}

	public static String toJson(Object obj) {
		return getGson().toJson(obj);
	}
}