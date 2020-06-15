package com.open.cloud.tools.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The type Jackson helper.
 * @author Leijian
 * @date 2020 /6/7
 */
public final class JacksonHelper {

	private static final PropertyNamingStrategy SNAKE_CASE_STRATEGY_UPPER = new SnakeCaseStrategyUpper();

	private static ObjectMapper objectMapper = new ObjectMapper();

	private static ObjectMapper writeValueAsStringIgnoreNull = new ObjectMapper();

	private static ObjectMapper writeValueAsStringSnakeCaseStrategyIgnoreNull = new ObjectMapper();

	private static ObjectMapper writeValueAsStringSnakeCaseStrategyUpperIgnoreNull = new ObjectMapper();

	private static ObjectMapper writeValueAsStringUpperCameLCaseIgnoreNull = new ObjectMapper();

	private static ObjectMapper writeValueAsStringLowerCaseIgnoreNull = new ObjectMapper();

	private static Map<String, String> hConv = new ConcurrentHashMap<>();

	private static final String S3 = "_";

	private JacksonHelper() {

	}

	static {
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		writeValueAsStringIgnoreNull.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		writeValueAsStringIgnoreNull.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		writeValueAsStringIgnoreNull.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		writeValueAsStringIgnoreNull.configure(SerializationFeature.INDENT_OUTPUT, true);
		writeValueAsStringIgnoreNull.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);

		writeValueAsStringSnakeCaseStrategyIgnoreNull.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		writeValueAsStringSnakeCaseStrategyIgnoreNull.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		writeValueAsStringSnakeCaseStrategyIgnoreNull.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		writeValueAsStringSnakeCaseStrategyIgnoreNull.configure(SerializationFeature.INDENT_OUTPUT, true);
		writeValueAsStringSnakeCaseStrategyIgnoreNull.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

		writeValueAsStringSnakeCaseStrategyUpperIgnoreNull.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		writeValueAsStringSnakeCaseStrategyUpperIgnoreNull.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		writeValueAsStringSnakeCaseStrategyUpperIgnoreNull.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		writeValueAsStringSnakeCaseStrategyUpperIgnoreNull.configure(SerializationFeature.INDENT_OUTPUT, true);
		writeValueAsStringSnakeCaseStrategyUpperIgnoreNull.setPropertyNamingStrategy(SNAKE_CASE_STRATEGY_UPPER);

		writeValueAsStringUpperCameLCaseIgnoreNull.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		writeValueAsStringUpperCameLCaseIgnoreNull.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		writeValueAsStringUpperCameLCaseIgnoreNull.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		writeValueAsStringUpperCameLCaseIgnoreNull.configure(SerializationFeature.INDENT_OUTPUT, true);
		writeValueAsStringUpperCameLCaseIgnoreNull.setPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE);

		writeValueAsStringLowerCaseIgnoreNull.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		writeValueAsStringLowerCaseIgnoreNull.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		writeValueAsStringLowerCaseIgnoreNull.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
		writeValueAsStringLowerCaseIgnoreNull.configure(SerializationFeature.INDENT_OUTPUT, true);
		writeValueAsStringLowerCaseIgnoreNull.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
	}

	/**
	 * Read tree json node.
	 *
	 * @param content the content
	 * @return the json node
	 */
	@SneakyThrows
	public static Map readValueToMap(String content) {
		return objectMapper.readValue(content, Map.class);
	}

	@SneakyThrows
	public static <T> T readValueToObject(String content, Class<T> tClass) {
		return objectMapper.readValue(content, tClass);
	}

	/**
	 * Read tree json node.
	 *
	 * @param content the content
	 * @return the json node
	 */
	@SneakyThrows
	public static JsonNode readTree(String content) {
		return objectMapper.readTree(content);
	}

	/**
	 * Read tree json node.
	 *
	 * @return the json node
	 */
	@SneakyThrows
	@Deprecated
	public static Map convertValueToMap(JsonNode jsonNode) {
		Map<String, Object> resultMap = new HashMap<>();
		Iterator<Map.Entry<String, JsonNode>> source = jsonNode.fields();
		while (source.hasNext()) {
			Map.Entry<String, JsonNode> entry = source.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (!(source instanceof JsonNode)) {
				resultMap.put(key, value);
				continue;
			}
			JsonNode element = (JsonNode) source;
			if (element.isArray()) {

			} else if (element.isContainerNode()) {
				resultMap.put(key, convertValueToMap(element));
			} else {
				//TODO 没想明白
			}
		}
		return resultMap;
	}

	/**
	 * javaBean、列表数组原样转换为json字符串,忽略空值
	 * @param obj the obj
	 * @return the string
	 * @throws Exception the exception
	 */
	@SneakyThrows
	public static String writeValueAsStringIgnoreNull(Object obj) {
		return writeValueAsStringIgnoreNull.writeValueAsString(obj);
	}

	/**
	 * javaBean、列表数组转换为json字符串,忽略空值,key转为大写。JavaBean转为下划线及小写模块
	 * eg：acctName--->acct_name
	 * @param obj the obj
	 * @return the string
	 * @throws Exception the exception
	 */
	@SneakyThrows
	public static String writeValueAsStringSnakeCaseStrategyIgnoreNull(Object obj) {
		return writeValueAsStringSnakeCaseStrategyIgnoreNull.writeValueAsString(obj);
	}

	/**
	 * javaBean、列表数组转换为json字符串,忽略空值,key转为大写。JavaBean转为下划线及小写模块
	 * eg：acctName--->ACCT_NAME
	 * @param obj the obj
	 * @return the string
	 * @throws Exception the exception
	 */
	@SneakyThrows
	public static String writeValueAsStringSnakeCaseStrategyUpperIgnoreNull(Object obj) {
		return writeValueAsStringSnakeCaseStrategyUpperIgnoreNull.writeValueAsString(obj);
	}

	/**
	 * javaBean、列表数组转换为json字符串,忽略空值,key转为大写。JavaBean首字母大写
	 * eg：acctName--->AcctName
	 * @param obj the obj
	 * @return string
	 * @throws Exception the exception
	 */
	@SneakyThrows
	public static String writeValueAsStringUpperCameLCaseIgnoreNull(Object obj) {
		return writeValueAsStringUpperCameLCaseIgnoreNull.writeValueAsString(obj);
	}

	/**
	 * javaBean、列表数组转换为json字符串,忽略空值,key转为大写。JavaBean整体小写
	 * @param obj the obj
	 * @return string
	 * @throws Exception the exception
	 */
	@SneakyThrows
	public static String writeValueAsStringLowerCaseIgnoreNull(Object obj) {
		return writeValueAsStringLowerCaseIgnoreNull.writeValueAsString(obj);
	}

	/**
	 * The type Snake case strategy upper.
	 */
	public static class SnakeCaseStrategyUpper extends PropertyNamingStrategy.PropertyNamingStrategyBase {
		@Override
		public String translate(String input) {
			if (input == null) {
				return input;
			}
			int length = input.length();
			StringBuilder result = new StringBuilder(length * 2);
			int resultLength = 0;
			boolean wasPrevTranslated = false;
			for (int i = 0; i < length; i++) {
				char c = input.charAt(i);
				if (i > 0 || c != '_') // skip first starting underscore
				{
					if (Character.isUpperCase(c)) {
						if (!wasPrevTranslated && resultLength > 0 && result.charAt(resultLength - 1) != '_') {
							result.append('_');
							resultLength++;
						}
						c = Character.toLowerCase(c);
						wasPrevTranslated = true;
					} else {
						wasPrevTranslated = false;
					}
					result.append(c);
					resultLength++;
				}
			}
			return resultLength > 0 ? result.toString().toUpperCase() : input.toUpperCase();
		}
	}

	/**
	 * The type Snake case strategy upper.
	 */
	public static class SnakeCaseStrategyToHump extends PropertyNamingStrategy.PropertyNamingStrategyBase {
		@Override
		public String translate(String input) {
			if (input == null) {
				return input;
			}
			return convertHumpCase(input);
		}
	}

	/**
	 * 大写+下划线格式转化为驼峰格式
	 *
	 * @param key
	 * @return
	 */
	public static String convertHumpCase(String key) {
		if (hConv.containsKey(key)) {
			return hConv.get(key);
		}
		StringBuilder sb = new StringBuilder();
		String[] fs = key.split(S3);
		int i = 0;
		for (String s : fs) {
			if (i == 0) {
				sb.append(s.toLowerCase());
			} else {
				sb.append(s.substring(0, 1).toUpperCase());
				sb.append(s.substring(1).toLowerCase());
			}
			i++;
		}
		String tmp = sb.toString();
		hConv.put(key, tmp);
		return tmp;
	}
}
