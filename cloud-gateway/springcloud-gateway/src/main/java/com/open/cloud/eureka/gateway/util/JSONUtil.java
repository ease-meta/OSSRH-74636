package com.open.cloud.eureka.gateway.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JSONUtil {
	public static JSONObject parseJsonString(final String json) {
		final JSONObject jsonFinal = new JSONObject();
		final LinkedHashMap<String, Object> jsonMap = (LinkedHashMap<String, Object>) JSON.parseObject(json, (TypeReference) new TypeReference<LinkedHashMap<String, Object>>() {
		}, new Feature[0]);
		for (final Map.Entry<String, Object> entry : jsonMap.entrySet()) {
			parseJsonMap(entry, jsonFinal);
		}
		jsonMap.toString();
		return jsonFinal;
	}

	public static JSONObject parseJsonMap(final Map.Entry<String, Object> entry, final JSONObject jsonObject) {
		if (entry.getValue() instanceof Map) {
			final LinkedHashMap<String, Object> jsonMap = (LinkedHashMap<String, Object>) JSON.parseObject(entry.getValue().toString(), (TypeReference) new TypeReference<LinkedHashMap<String, Object>>() {
			}, new Feature[0]);
			JSONObject tempJson = new JSONObject();
			for (final Map.Entry<String, Object> entry2 : jsonMap.entrySet()) {
				tempJson = parseJsonMap(entry2, tempJson);
			}
			jsonObject.put(toHump(entry.getKey()), (Object) tempJson);
		}
		if (entry.getValue() instanceof List) {
			final List list = (List) entry.getValue();
			final JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < list.size(); ++i) {
				jsonArray.add((Object) parseJsonString(list.get(i).toString()));
			}
			jsonObject.put((String) entry.getKey(), (Object) jsonArray);
		}
		if (entry.getValue() instanceof String) {
			jsonObject.put(toHump(entry.getKey()), entry.getValue());
		}
		return jsonObject;
	}

	public static String toHump(final String origin) {
		final String[] array = origin.split("_");
		final StringBuilder builder = new StringBuilder();
		for (String str : array) {
			str = str.toLowerCase();
			builder.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
		}
		return Character.toLowerCase(builder.charAt(0)) + builder.substring(1);
	}

	private JSONUtil() {
	}
}
