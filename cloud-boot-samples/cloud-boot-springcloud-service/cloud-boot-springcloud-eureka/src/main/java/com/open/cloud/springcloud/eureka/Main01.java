package com.open.cloud.springcloud.eureka;

import org.apache.commons.configuration2.YAMLConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;

/**
 * @author Leijian
 * @date 2020/9/23
 */
public class Main01 {


	public static void main(String[] args) throws Exception {
		YAMLConfiguration config = new YAMLConfiguration();
		Resource resource = new ClassPathResource("application-dev.yml");
		File file = resource.getFile();
		config.read(new InputStreamReader(new FileInputStream(file)));

		Iterator<String> keys = config.getKeys();
		while (keys.hasNext()) {
			String key = keys.next();
			String value = config.getString(key);
			System.out.println(key + " = " + value);
		}
	}
}
