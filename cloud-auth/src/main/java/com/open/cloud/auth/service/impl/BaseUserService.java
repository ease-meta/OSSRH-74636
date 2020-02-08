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
package com.open.cloud.auth.service.impl;

import com.open.cloud.auth.entity.UserPo;
import com.open.cloud.auth.repository.UserRepository;
import com.open.cloud.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Leijian
 * @date   2020/2/8
 */
@Service
@Primary
@Slf4j
public class BaseUserService implements UserService {

	private final static Map<Integer, String> ENCODER_TYPE = new HashMap<>();

	private final static Map<String, PasswordEncoder> ENCODER_MAP = new HashMap<>();

	private final static String PASSWORD_FORMAT = "{%s}%s";

	private final UserRepository userRepository;

	public BaseUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	static {
		ENCODER_TYPE.put(0, "noop");
		ENCODER_TYPE.put(1, "bcrypt");
		ENCODER_TYPE.put(2, "pbkdf2");
		ENCODER_TYPE.put(3, "scrypt");
		ENCODER_TYPE.put(4, "sha256");
		ENCODER_MAP.put("noop", NoOpPasswordEncoder.getInstance());
		ENCODER_MAP.put("bcrypt", new BCryptPasswordEncoder());
		ENCODER_MAP.put("pbkdf2", new Pbkdf2PasswordEncoder());
		ENCODER_MAP.put("scrypt", new SCryptPasswordEncoder());
		ENCODER_MAP.put("sha256", new StandardPasswordEncoder());
	}

	@Override
	public void insert(UserPo userDO) {
		String username = userDO.getUserName();
		if (exist(username)) {
			throw new RuntimeException("用户名已存在！");
		}
		// 随机使用加密方式
		Random random = new Random();
		int x = random.nextInt(5);
		String encoderType = ENCODER_TYPE.get(x);
		PasswordEncoder passwordEncoder = ENCODER_MAP.get(encoderType);
		userDO.setPassword(String.format(PASSWORD_FORMAT, encoderType, passwordEncoder.encode(userDO.getPassword())));
		userRepository.save(userDO);
	}

	@Override
	public UserPo getByUsername(String username) {
		return userRepository.findUserByName(username);
	}

	/**
	 * 判断用户是否存在
	 */
	private boolean exist(String username) {
		UserPo userDO = userRepository.findUserByName(username);
		return (userDO != null);
	}
}
