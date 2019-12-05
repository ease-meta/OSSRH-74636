package com.open.cloud.es.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;


@Controller
public class TemplatesController {

	@RequestMapping("/")
	String test(HttpServletRequest request) {
		//逻辑处理
		request.setAttribute("key", "hello world");
		return "redirect:/index.html";
	}
}
