package com.open.cloud.eureka.gateway.util;

import java.util.*;
import java.util.regex.*;

public class PatternUtil {
	public static final Pattern BASE_PATTERN;
	public static final Pattern BASE_LIMITER_PATTERN;
	public static final Pattern TEMP_LIMITER_PATTERN;
	public static final Pattern PATTERN_SERVICE;
	public static final Pattern PATTERN_INSTANCE;
	public static final Pattern PATTERN_INSTANCEID;
	public static final Pattern temp;
	public static final Pattern TOKEN_GET;
	public static final Pattern REMOVE_SCHEMA;
	public static final Pattern GET_URL_PATH;
	public static final Pattern IP_ADDRESS;
	public static final Pattern IS_INTEGER;

	public static void main(final String[] args) {
		final Map<String, String> map = new HashMap<String, String>();
		map.remove("a");
		map.put("a", "b");
		map.replace("a", "c");
		System.out.println("test result:" + map.get("a"));
		final Matcher matcher = PatternUtil.PATTERN_SERVICE.matcher("lb://yanggyc-hello-worlds");
		while (matcher.find()) {
			System.out.println(matcher.group());
		}
		final String origin = "MY_POLICY_TEST";
		final String[] array = origin.split("_");
		final StringBuilder builder = new StringBuilder();
		for (String str : array) {
			str = str.toLowerCase();
			builder.append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
		}
		System.out.println(Character.toLowerCase(builder.charAt(0)) + builder.substring(1));
		final Matcher matcher2 = PatternUtil.TOKEN_GET.matcher("Bearer 0c027066-0da4-4184-b5be-6ea70598cbe5");
		while (matcher2.find()) {
			System.out.println(matcher2.group());
		}
	}

	static {
		BASE_PATTERN = Pattern.compile("^((?!\\$).)*$");
		BASE_LIMITER_PATTERN = Pattern.compile("\\$base$");
		TEMP_LIMITER_PATTERN = Pattern.compile("\\$temp$");
		PATTERN_SERVICE = Pattern.compile("(?<=lb://)[^\\s\\/]+");
		PATTERN_INSTANCE = Pattern.compile("^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\/\\s]+");
		PATTERN_INSTANCEID = Pattern.compile("(?<=(https|http|ftp|rtsp|mms)://)[^\\s\\/]+");
		temp = Pattern.compile("(?<=\\{)[^\\}]+");
		TOKEN_GET = Pattern.compile("(?<=Bearer\\s)[^\\s]+");
		REMOVE_SCHEMA = Pattern.compile("(?<=(lb|https|http|ftp|rtsp|mms)://)\\S*");
		GET_URL_PATH = Pattern.compile("(?<=\\/)\\S*");
		IP_ADDRESS = Pattern.compile("^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(00?\\d|1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$");
		IS_INTEGER = Pattern.compile("^[0-9]*[1-9][0-9]*$");
	}
}
