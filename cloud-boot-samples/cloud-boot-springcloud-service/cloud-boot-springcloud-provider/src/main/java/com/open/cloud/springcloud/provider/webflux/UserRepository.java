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
package com.open.cloud.springcloud.provider.webflux;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {

	//模拟数据库存储
	private static Map<Integer, User> userMap = new HashMap<>();

	//初始化仓储数据
	static {
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("用户1");
		userMap.put(1, user1);
		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("用户2");
		userMap.put(2, user2);
	}

	public Map<Integer, User> getUserByUserId() {
		printlnThread("调用getUserByUserId");
		return userMap;
	}

	/**
	 * 打印当前线程
	 * @param object
	 */
	private void printlnThread(Object object) {
		String threadName = Thread.currentThread().getName();
		System.out.println("HelloWorldAsyncController[" + threadName + "]: " + object);
	}

	public Map<Integer, User> getUsers() {
		printlnThread("调用getUsers");
		return userMap;
	}
}