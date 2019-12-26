package com.open.cloud.eureka.gateway.controller;

import com.google.common.base.Strings;
import com.open.cloud.eureka.gateway.common.ResultDO;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.service.InstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/gateway"})
public class InstanceController {
	@Autowired
	DiscoveryClient discoveryClient;
	@Qualifier("EurekaInstanceService")
	@Autowired
	InstanceService instanceService;

	@GetMapping({"/instances"})
	public ResponseEntity<?> getInstances() {
		return (ResponseEntity<?>) ResponseEntity.ok((Object) instanceService.getServiceInstances());
	}

	@DeleteMapping({"/instance"})
	public ResponseEntity<?> deleteInstance(@RequestParam final String serviceId, @RequestParam final String instanceId) {
		if (!instanceService.deleteInstance(serviceId, instanceId)) {
			throw new CommonException().setCode("1017").setMsg("service or instance does not exist");
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	@PutMapping({"/instance"})
	public ResponseEntity<?> updateInstanceStatus(@RequestParam final String serviceId, @RequestParam final String instanceId, @RequestParam final String status) {
		if (Strings.isNullOrEmpty(serviceId) || Strings.isNullOrEmpty(instanceId)) {
			throw new CommonException().setCode("1005").setMsg("serviceId and instanceId cannot be empty");
		}
		if (!statusCheck(status)) {
			throw new CommonException().setCode("1005").setMsg("Status must be UP, DOWN, OUT_OF_SERVICE");
		}
		if (instanceService.updateInstanceStatus(serviceId, instanceId, status.toUpperCase())) {
			return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
		}
		throw new CommonException().setCode("1017").setMsg("service or instance does not exist");
	}

	@DeleteMapping({"/service"})
	public ResponseEntity<?> deleteService(@RequestParam final String serviceId) {
		if (!instanceService.deleteService(serviceId)) {
			throw new CommonException().setCode("1017").setMsg("service does not exist");
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	private boolean statusCheck(final String status) {
		final String upperCase = status.toUpperCase();
		switch (upperCase) {
			case "UP":
			case "DOWN":
			case "OUT_OF_SERVICE":
				return true;
			default:
				return false;

		}
	}
}
