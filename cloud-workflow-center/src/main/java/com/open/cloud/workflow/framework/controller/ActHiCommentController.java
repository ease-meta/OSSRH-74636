package com.open.cloud.workflow.framework.controller;


import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 审批意见表和流程中的附件信息表 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Controller
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "审批意见")
public class ActHiCommentController {

}

