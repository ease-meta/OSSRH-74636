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
package com.example.demo.service;

import com.example.demo.api.UserService;
import com.example.demo.api.model.UserModel;
import com.example.demo.dao.dataobject.UserDO;
import com.example.demo.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:chenxilzx1@gmail.com">theonefx</a>
 */
@Component
public class UserServiceImpl implements UserService {

	private static final BeanCopier copier = BeanCopier
			.create(UserModel.class, UserDO.class, false);
	@Autowired
	private UserMapper userMapper;

	public String getUserName(Long id) {
		UserDO userDO = userMapper.getById(id);
		return userDO != null ? userDO.getName() : null;
	}

	public UserModel addUser(UserModel user) {
		UserDO userDO = new UserDO();
		copier.copy(user, userDO, null);

		Long id = userMapper.insert(userDO);
		user.setId(id);
		return user;
	}
}
