package com.moc.cloud.workflow.framework.controller;


import com.moc.cloud.workflow.common.enums.AuthTypeEnum;
import com.moc.cloud.workflow.common.exception.BusinessException;
import com.moc.cloud.workflow.common.responses.Responses;
import com.moc.cloud.workflow.framework.entity.ActAssignee;
import com.moc.cloud.workflow.framework.annotation.Resources;
import com.moc.cloud.workflow.framework.config.BaseController;
import com.moc.cloud.workflow.framework.serviceImpl.ActAssigneeServiceImpl;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 流程定义审批人 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@RestController
@Api(tags = "节点审批人")
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ActAssigneeController extends BaseController<Object> {

    @Autowired
    ActAssigneeServiceImpl actAssigneeService;

    /**
     * 节点处理人新增，ID是后台自动生成
     *
     * @return
     */
    @Resources(auth = AuthTypeEnum.LOGIN, description = "新增节点处理人")
    @PostMapping(value = {"/assignee"})
    public Responses<Void> post(@RequestBody ActAssignee actAssignee) {
        actAssigneeService.insertSelective(actAssignee);
        return responses(HttpStatus.OK);
    }

    /**
     * 获取节点的处理人，根据key和sid生成
     *
     * @param key
     * @return
     */
    @Resources(auth = AuthTypeEnum.LOGIN, description = "获取节点处理人")
    @GetMapping(value = {"/assignee/{key}/{sid}"})
    public Responses<List<ActAssignee>> get(@PathVariable(required = false) String key, @PathVariable(required = false) String sid) {
        if (StringUtils.isAllEmpty(key, sid)) {
            throw new BusinessException("参数不合法，key和sid都不能为空");
        }
        ActAssignee actAssignee = new ActAssignee();
        actAssignee.setKey(key);
        actAssignee.setSid(sid);
        List<ActAssignee> actAssigneeList = actAssigneeService.selectSelective(actAssignee);
        return responses(HttpStatus.OK, actAssigneeList);
    }

    /**
     * 更新节点审批人
     *
     * @param actAssignee
     * @return
     */
    @Resources(auth = AuthTypeEnum.LOGIN, description = "更新节点处理人")
    @PatchMapping(value = {"/assignee"})
    public Responses<Void> patch(@RequestBody ActAssignee actAssignee) {
        actAssigneeService.updateByPrimaryKeySelective(actAssignee);
        return responses(HttpStatus.OK);
    }

}

