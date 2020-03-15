package com.open.cloud.eureka.server.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContext;
import com.netflix.eureka.cluster.PeerEurekaNodes;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.open.cloud.common.base.ResponseTool;
import com.open.cloud.eureka.server.service.EurekaInstacneZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class EurekaServerController {

	@Autowired
	EurekaInstacneZoneService zoneService;

	@Autowired
	EurekaServerContext eurekaServerContext;


	@GetMapping("/getZone")
	public ResponseEntity<?> getEurekaZone() {
		return ResponseEntity.ok(ResponseTool.success(zoneService.getEurekaZone()));
	}

	@GetMapping("/getServiceByZone")
	public ResponseEntity<?> getServiceByZone(@RequestParam String zoneName) {
		return ResponseEntity.ok(ResponseTool.success(zoneService.getServiceByZoneName(zoneName)));
	}

	@GetMapping("/downMeta")
	public ResponseEntity<?> setVersion(@RequestParam String name, @RequestParam String value
			, @RequestParam String status) {
		return ResponseEntity.ok(ResponseTool.success(zoneService.setVersionDown(name, value, status)));
	}

	@GetMapping("/test")
	public String test() {
		InstanceInfo instanceInfo = eurekaServerContext.getApplicationInfoManager().getInfo();
		PeerAwareInstanceRegistry peerAwareInstanceRegistry = eurekaServerContext.getRegistry();
		PeerEurekaNodes peerEurekaNodes = eurekaServerContext.getPeerEurekaNodes();
		Applications applications = peerAwareInstanceRegistry.getApplications();
		return "success";
	}
}
