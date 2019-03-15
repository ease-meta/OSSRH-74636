package com.open.cloud.workflow.framework.controller;


import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.SysRole;
import com.open.cloud.workflow.framework.serviceImpl.SysRoleServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@RestController
@Api(tags = "角色信息")
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysRoleController extends BaseController<SysRole> {

    @Autowired
    SysRoleServiceImpl sysRoleService;



    /**
     * 获取角色
     *
     * @param roleId
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "获取角色信息")
    @RequestMapping(value = {"/role/{roleId}"}, method = {RequestMethod.GET})
    public Responses<SysRole> getUser(@PathVariable(required = true) String roleId, HttpServletRequest request) {
        SysRole sysRole = sysRoleService.getCacheById(roleId);
        return responses(HttpStatus.OK, sysRole);
    }

    /**
     * 获取角色
     *
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "获取角色")
    @RequestMapping(value = {"/role"}, method = {RequestMethod.GET})
    public Responses<List<SysRole>> list(HttpServletRequest request, HttpServletResponse response) {
        List<SysRole> sysRoleList = sysRoleService.selectAll();
        return responses(HttpStatus.OK, sysRoleList);
    }

    /**
     * 删除用户
     *
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "删除角色信息")
    @RequestMapping(value = {"/role/{roleId}"}, method = {RequestMethod.DELETE})
    public Responses<Void> delete(@PathVariable(required = true) String roleId, HttpServletRequest request, HttpServletResponse response) {
        sysRoleService.deleteByPrimaryKey(roleId);
        response.setStatus(HttpStatus.NO_CONTENT.value());
        return responses(HttpStatus.OK);
    }

    /**
     * @param sysRole
     * @param request
     * @param response
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "维护角色信息")
    @RequestMapping(value = {"/role"}, method = {RequestMethod.PUT})
    public Responses<Void> put(@RequestBody(required = true) SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
        sysRoleService.updateByPrimaryKeySelective(sysRole);
        return responses(HttpStatus.OK);
    }

    /**
     * @param sysRole
     * @param request
     * @param response
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN, description = "新增角色信息")
    @RequestMapping(value = {"/role"}, method = {RequestMethod.POST})
    public Responses<Void> post(@RequestBody(required = true) SysRole sysRole, HttpServletRequest request, HttpServletResponse response) {
        sysRoleService.insertSelective(sysRole);
        return responses(HttpStatus.OK);
    }




}


