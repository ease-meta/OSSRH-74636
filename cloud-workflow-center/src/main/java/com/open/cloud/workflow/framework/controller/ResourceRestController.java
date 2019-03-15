package com.open.cloud.workflow.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.serviceImpl.ScanMappings;

/**
 * 
 * @author leijian
 * @date 2019年1月26日
 */
@RestController
@RequestMapping(value = "${api.version}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Validated
public class ResourceRestController extends BaseController<Object> {

	@Autowired
	private ScanMappings scanMappings;

	@Resources(auth=AuthTypeEnum.OPEN,description="资源定义")
	@PutMapping(value = "/resources")
	public Responses<Void> refresh() {
		scanMappings.doScan();
		return responses(HttpStatus.OK);
	}

}
