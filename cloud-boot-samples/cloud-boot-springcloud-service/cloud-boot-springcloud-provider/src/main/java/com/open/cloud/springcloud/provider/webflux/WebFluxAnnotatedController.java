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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/annotated/")
public class WebFluxAnnotatedController {

	@Autowired
	private UserRepository userRepository;

	/**
	 * 查询单个用户
	 * @param id
	 * @return 返回Mono 非阻塞单个结果
	 */
	@GetMapping("user/{id}")
	public Mono<User> getUserByUserId(@PathVariable("id") int id) {
		return Mono.just(userRepository.getUserByUserId().get(id));
	}

	/**
	 *
	 * @return 返回Flux 非阻塞序列
	 */
	@GetMapping("users")
	public Flux<User> getAll() {
		printlnThread("获取HTTP请求");
		//使用lambda表达式
		return Flux.fromStream(userRepository.getUsers().entrySet().stream().map(Map.Entry::getValue));
	}

	/**
	 * 打印当前线程
	 * @param object
	 */
	private void printlnThread(Object object) {
		String threadName = Thread.currentThread().getName();
		System.out.println("HelloWorldAsyncController[" + threadName + "]: " + object);
	}
}