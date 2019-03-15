package com.open.cloud.workflow.framework.controller;

import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.ActReDeployment;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-07
 */
@RestController
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActReDeploymentController extends BaseController<ActReDeployment> {

}
