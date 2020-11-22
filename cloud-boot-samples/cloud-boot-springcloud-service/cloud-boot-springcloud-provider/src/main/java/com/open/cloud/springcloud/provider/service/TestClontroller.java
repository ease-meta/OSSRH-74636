package com.open.cloud.springcloud.provider.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestClontroller {
	@PostMapping("/cif/nfin/client/restraint")
	public Core12000122Out runService(@RequestBody Core12000122In in) {
		return new Core12000122Out();
	}
}