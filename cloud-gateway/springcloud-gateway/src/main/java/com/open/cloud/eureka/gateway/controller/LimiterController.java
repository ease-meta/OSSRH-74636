package com.open.cloud.eureka.gateway.controller;

import com.google.common.base.Strings;
import com.open.cloud.eureka.gateway.common.ResultDO;
import com.open.cloud.eureka.gateway.exception.CommonException;
import com.open.cloud.eureka.gateway.model.LimiterDefinition;
import com.open.cloud.eureka.gateway.model.dto.LimiterDefinitionDTO;
import com.open.cloud.eureka.gateway.service.LimiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/gateway"})
public class LimiterController {
	@Autowired
	LimiterService limiterService;

	@GetMapping({"/limiter"})
	public ResponseEntity<?> getLimiter(@RequestParam(value = "serviceId", defaultValue = "") final String serviceId) {
		if (Strings.isNullOrEmpty(serviceId)) {
			throw new CommonException().setCode("1005").setMsg("serviceId cannot be empty");
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success").setData(this.limiterService.getLimiter(serviceId)));
	}

	@PostMapping({"/limiter"})
	public ResponseEntity<?> addAndUpdateLimiter(@RequestParam(value = "serviceId", defaultValue = "") final String serviceId, @RequestParam(value = "instanceId", defaultValue = "") final String instanceId, @RequestParam(value = "type", defaultValue = "base") final String type, @RequestBody final List<LimiterDefinitionDTO> limiterDefinitionDTOs) {
		if (!Strings.isNullOrEmpty(serviceId) && Strings.isNullOrEmpty(instanceId)) {
			this.limiterService.addAndUpdateServiceLimiter(serviceId.toLowerCase(), type, limiterDefinitionDTOs);
		} else {
			if (Strings.isNullOrEmpty(serviceId) || Strings.isNullOrEmpty(instanceId)) {
				throw new CommonException().setCode("1005").setMsg("serviceId or instanceId cannot be empty");
			}
			this.limiterService.addAndUpdateInstanceLimiter(serviceId.toLowerCase(), instanceId, type, limiterDefinitionDTOs);
		}
		return (ResponseEntity<?>) ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	@PutMapping({"/limiter"})
	public ResponseEntity updateLimiter(@RequestParam(value = "serviceId", defaultValue = "") final String serviceId, @RequestParam(value = "instanceId", defaultValue = "") final String instanceId, @RequestBody final List<LimiterDefinitionDTO> limiterDefinitionDTOs) {
		final List<LimiterDefinition> limiterDefinition = new ArrayList<LimiterDefinition>();
		for (final LimiterDefinitionDTO LimiterDefinitionDTO : limiterDefinitionDTOs) {
			limiterDefinition.add(this.limiterService.resolverDTOtoLimiter(LimiterDefinitionDTO));
		}
		if (!Strings.isNullOrEmpty(serviceId) && Strings.isNullOrEmpty(instanceId)) {
			this.limiterService.updateServiceLimiterList(serviceId, limiterDefinition);
		} else {
			if (Strings.isNullOrEmpty(serviceId) || Strings.isNullOrEmpty(instanceId)) {
				throw new CommonException().setCode("1005").setMsg("serviceId or instanceId cannot be empty");
			}
			this.limiterService.updateInstanceLimiterList(serviceId, instanceId, limiterDefinition);
		}
		return ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}

	@DeleteMapping({"/limiter"})
	public ResponseEntity deleteLimiter(@RequestParam(value = "serviceId", defaultValue = "") final String serviceId, @RequestParam(value = "instanceId", defaultValue = "") final String instanceId, @RequestParam(value = "limiterId", defaultValue = "") final String limiterId) {
		if (!Strings.isNullOrEmpty(serviceId) && Strings.isNullOrEmpty(instanceId)) {
			this.limiterService.deleteServiceLimiter(serviceId, limiterId);
		} else {
			if (Strings.isNullOrEmpty(serviceId) || Strings.isNullOrEmpty(instanceId)) {
				throw new CommonException().setCode("1005").setMsg("serviceId or instanceId cannot be empty");
			}
			this.limiterService.deleteInstanceLimiter(serviceId, instanceId, limiterId);
		}
		return ResponseEntity.ok((Object) new ResultDO().setCode("0000").setMessage("Success"));
	}
}
