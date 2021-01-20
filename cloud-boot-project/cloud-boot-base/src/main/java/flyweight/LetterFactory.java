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
package flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shadow
 * @Date 2016年8月5日下午8:05:27
 * @Fun 一个产生字母对象的 享元工厂（单例工厂）
 **/
public class LetterFactory {
	private static LetterFactory instance = new LetterFactory();
	private Map<String, Letter> map;

	private LetterFactory() {
		map = new HashMap<>();
	}

	public static LetterFactory getInstance() {
		return instance;
	}

	public void add(Letter letter) {
		if (letter != null && !map.containsKey(letter.getName())) {
			map.put(letter.getName(), letter);
		}

		System.out.println("map.size ==== " + map.size());
	}

	public Letter get(String name) {
		return map.get(name);
	}
}
