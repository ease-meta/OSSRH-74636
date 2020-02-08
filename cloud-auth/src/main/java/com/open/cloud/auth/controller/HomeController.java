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
