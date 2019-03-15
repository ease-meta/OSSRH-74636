package com.open.cloud.workflow.framework.controller;


import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.ReprocdefFlow;
import com.open.cloud.workflow.framework.serviceImpl.ReprocdefFlowServiceImpl;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 流程定义节点信息 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@RestController
@Slf4j
@Api(tags = "节点信息")
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReprocdefFlowController extends BaseController<ReprocdefFlow> {
    @Autowired
    ReprocdefFlowServiceImpl reprocdefFlowService;

    /**
     * 节点信息查询
     *
     * @param
     * @param request
     * @param response
     * @return
     */
    @Resources(auth = AuthTypeEnum.LOGIN,description = "流程审批节点信息查询")
    @RequestMapping(value = {"/flow/flows"}, method = {RequestMethod.GET})
    public Responses<List<ReprocdefFlow>> get(@RequestParam(value = "key", required = true) String key, HttpServletRequest request, HttpServletResponse response) {
        if (log.isDebugEnabled()) {
            log.info("前端系统传入的值{}", key);
        }
        List<ReprocdefFlow> list = reprocdefFlowService.seletFlowsByKey(key).stream().filter(flow -> {
            String getSidType = flow.getSidType();
            return !(StringUtils.contains(getSidType, "Gateway"));
        }).collect(Collectors.toList());
        return responses(HttpStatus.OK, list);
    }


}

