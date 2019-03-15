package com.open.cloud.workflow.framework.controller;


import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.SysPermission;
import com.open.cloud.workflow.framework.serviceImpl.SysPermissionServiceImpl;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 权限信息表 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@RestController
@Api(tags = "工作組")
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysPermissionController extends BaseController<SysPermission> {

    @Autowired
    SysPermissionServiceImpl sysPermissionService;

    /**
     * 获取指定权限
     *
     * @param
     * @param
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "查询权限信息")
    @RequestMapping(value = {"/permission"}, method = {RequestMethod.GET})
    public Responses<List<SysPermission>> listSysPermissions() {
        List<SysPermission> sysPermissionList = sysPermissionService.selectAll();
        return responses(HttpStatus.OK, sysPermissionList);
    }

    /**
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "删除权限")
    @RequestMapping(value = {"/permission/{permissionId}"}, method = {RequestMethod.DELETE})
    public Responses<Void> delete(@PathVariable(required = true) String permissionId, HttpServletRequest request, HttpServletResponse response) {
        sysPermissionService.deleteByPrimaryKey(permissionId);
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return responses(HttpStatus.OK);
    }

    /**
     * @param sysPermission
     * @param request
     * @param response
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "权限信息维护")
    @RequestMapping(value = {"/permission"}, method = {RequestMethod.PUT})
    public Responses<Void> put(@RequestBody(required = true) SysPermission sysPermission, HttpServletRequest request, HttpServletResponse response) {
        sysPermissionService.updateByPrimaryKeySelective(sysPermission);
        return responses(HttpStatus.OK);
    }

    /**
     *
     * @param sysPermission
     * @param request
     * @param response
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "新增权限")
    @PostMapping(value = {"/permission"})
    public Responses<Void> post(@RequestBody(required = true) SysPermission sysPermission, HttpServletRequest request, HttpServletResponse response) {
        sysPermissionService.insertSelective(sysPermission);
        return responses(HttpStatus.OK);
    }

}

