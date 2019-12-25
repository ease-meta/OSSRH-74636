package com.open.cloud.eureka.gateway.util;

import com.alibaba.fastjson.parser.*;
import com.alibaba.fastjson.*;

import java.util.*;

public class BodyCheckUtil {
	public static Boolean getBodyValue(final String name, final String value, final String jsonString) {
		final LinkedHashMap<String, Object> jsonMap = (LinkedHashMap<String, Object>) JSON.parseObject(jsonString, (TypeReference) new TypeReference<LinkedHashMap<String, Object>>() {
		}, new Feature[0]);
		final String[] bodyNames = name.split("\\.");
		HashMap<String, Object> map = null;
		int i = 0;
		while (i < bodyNames.length) {
			final Object key = jsonMap.get(bodyNames[i]);
			if (map == null && key instanceof JSONObject) {
				final JSONObject jsonObject = (JSONObject) key;
				map = (HashMap<String, Object>) jsonObject.getInnerMap();
				++i;
			} else if (map == null && key instanceof JSONArray) {
				if (getArrayValue(value, i + 1, bodyNames, (JSONArray) key)) {
					return true;
				}
				return false;
			} else if (map != null && map.get(bodyNames[i]) instanceof JSONArray) {
				if (getArrayValue(value, i + 1, bodyNames, (JSONArray) map.get(bodyNames[i]))) {
					return true;
				}
				return false;
			} else {
				final Object entry = map.get(bodyNames[i]);
				if (getObjectValue(entry, value, map)) {
					return true;
				}
				return false;
			}
		}
		return false;
	}

	private static Boolean getArrayValue(final String value, final Integer index, final String[] bodyNames, final JSONArray array) {
		HashMap map = null;
		for (final Object single : array) {
			final int i = index;
			if (map == null && single instanceof JSONObject) {
				map = (HashMap) ((JSONObject) single).getInnerMap();
			}
			final Object entry = map.get(bodyNames[i]);
			if (entry instanceof JSONArray) {
				if (getArrayValue(value, i + 1, bodyNames, (JSONArray) entry)) {
					return true;
				}
				continue;
			} else {
				if (getObjectValue(entry, value, map)) {
					return true;
				}
				continue;
			}
		}
		return false;
	}

	private static Boolean getObjectValue(final Object entry, final String value, HashMap<String, Object> map) {
		if (entry instanceof JSONObject) {
			map = (HashMap<String, Object>) ((JSONObject) entry).getInnerMap();
		} else if (value.equals(entry)) {
			return true;
		}
		return false;
	}
}
