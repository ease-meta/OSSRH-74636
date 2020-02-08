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
package com.open.cloud.auth.controller;

import com.open.cloud.auth.entity.UserPo;
import com.open.cloud.auth.service.UserService;
import com.open.cloud.common.base.Response;
import com.open.cloud.common.base.ResponseTool;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HomeController {

	private final UserService userService;

	@PostMapping("/register")
	public Response<Void> doRegister(@RequestBody UserPo userPo) {
		// 此处省略校验逻辑
		userService.insert(userPo);
		return ResponseTool.success();
	}

}
