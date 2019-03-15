package com.open.cloud.workflow.framework.controller;


import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.SysUserPermission;
import com.open.cloud.workflow.framework.serviceImpl.SysUserPermissionServiceImpl;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色用户信息对照表 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@RestController
@Api(tags = "用户权限对照")
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysUserPermissionController extends BaseController<SysUserPermission> {

    @Autowired
    SysUserPermissionServiceImpl sysUserPermissionService;

    /**
     * 根据角色查询角色下的用户
     *
     * @param userId
     * @param request
     * @return
     */


    /**
     * 根据用户查询用户所属的角色
     *
     * @param request
     * @return
     */


    /**
     *
     * 更新状态角色和用户状态
     * @param request
     * @return
     */


    /**
     * 删除角色和用户的对应关系
     * @param sysUser
     * @param request
     * @param response
     * @return
     */


}
