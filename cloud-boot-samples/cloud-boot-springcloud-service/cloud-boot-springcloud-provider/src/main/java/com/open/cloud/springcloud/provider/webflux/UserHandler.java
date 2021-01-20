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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class UserHandler {

	@Autowired
	private UserRepository userRepository;

	public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
		printlnThread("获取单个用户");
		return ServerResponse.status(HttpStatus.OK).body(
				Mono.just(userRepository.getUserByUserId().get(
						Integer.valueOf(serverRequest.pathVariable("userId")))), User.class);
	}

	/**
	 * 打印当前线程
	 * @param object
	 */
	private void printlnThread(Object object) {
		String threadName = Thread.currentThread().getName();
		System.out.println("HelloWorldAsyncController[" + threadName + "]: " + object);
	}

	public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
		printlnThread("获取所有用户");
		Flux<User> userFlux = Flux.fromStream(userRepository.getUsers().entrySet().stream().map(Map.Entry::getValue));
		return ServerResponse.ok()
				.body(userFlux, User.class);
	}
}