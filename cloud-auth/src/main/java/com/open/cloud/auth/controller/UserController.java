package com.open.cloud.auth.controller;

import com.open.cloud.common.base.Response;
import com.open.cloud.common.base.ResponseTool;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

	@GetMapping("/user")
	public Response<String> user(@AuthenticationPrincipal Principal principal) {
		return ResponseTool.success(principal.getName());
	}

	@GetMapping("/admin")
	public Response<String> admin(@AuthenticationPrincipal Principal principal) {
		return ResponseTool.success(principal.getName());
	}

}
