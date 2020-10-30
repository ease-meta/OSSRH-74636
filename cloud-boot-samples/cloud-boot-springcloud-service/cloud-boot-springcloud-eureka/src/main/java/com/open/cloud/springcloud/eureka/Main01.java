/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.springcloud.eureka;

import java.util.function.Function;

/**
 * @author Leijian
 * @date 2020/9/23
 */
public class Main01 {

	/*public static void main(String[] args) throws Exception {
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
	}*/

	public static void main(String[] args) {

		Function<Integer, Integer> name = new Function<Integer, Integer>() {
			@Override
			public Integer apply(Integer integer) {
				return integer * 2;
			}
		};
		//Function<Integer, Integer> name = e -> e * 2;
		Function<Integer, Integer> square = e -> e * e;
		int value = name.andThen(square).apply(3);
		System.out.println("andThen value=" + value);
		int value2 = name.compose(square).apply(3);
		System.out.println("compose value2=" + value2);
		//返回一个执行了apply()方法之后只会返回输入参数的函数对象
		Object identity = Function.identity().apply("huohuo");
		System.out.println(identity);
	}
}
