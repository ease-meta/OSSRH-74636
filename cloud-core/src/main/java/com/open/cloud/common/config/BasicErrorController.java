package com.open.cloud.common.config;

import com.open.cloud.common.base.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@Slf4j
public class BasicErrorController extends BaseController<Object> implements ErrorController {

	@Override
	public String getErrorPath() {
		return "error";
	}

	@GetMapping("/error")
	@ResponseBody
	public Response<Void> error(HttpServletRequest request, HttpServletResponse response) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String message = (String) request.getAttribute("javax.servlet.error.message");
		String uri = (String) request.getAttribute("javax.servlet.error.request_uri");
		Throwable t = (Throwable) request.getAttribute("javax.servlet.error.exception");
		StringBuilder s = new StringBuilder();
		s.append(getBlock(t == null ? "page" : "exception"));
		s.append(getBlock(statusCode));
		s.append(getBlock(message));

		s.append(getBlock(uri));
		s.append(getBlock(request.getHeader("Referer")));
		StringWriter sw = new StringWriter();

		while (t != null) {
			t.printStackTrace(new PrintWriter(sw));
			t = t.getCause();
		}
		s.append(getBlock(sw.toString()));
		log.error("{}",s.toString());
		return response(HttpStatus.valueOf(statusCode));
	}

	private static String getBlock(Object msg) {
		if (msg == null) {
			msg = "";
		}
		return "[" + msg.toString() + "]";
	}

}
