package com.open.cloud.workflow.framework.controller;

import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.SysUser;
import com.open.cloud.workflow.framework.entity.SysUserPermission;
import com.open.cloud.workflow.framework.entity.SysUserRole;
import com.open.cloud.workflow.framework.serviceImpl.SysUserPermissionServiceImpl;
import com.open.cloud.workflow.framework.serviceImpl.SysUserRoleServiceImpl;
import com.open.cloud.workflow.framework.serviceImpl.SysUserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
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
 * 用户信息表 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Api(tags = "用户")
@RestController
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysUserController extends BaseController<SysUser> {

    @Autowired
    SysUserServiceImpl sysUserServiceImpl;

    @Autowired
    SysUserRoleServiceImpl sysUserRoleService;

    @Autowired
    SysUserPermissionServiceImpl sysUserPermissionService;

    /**
     * 获取指定用户
     *
     * @param userId
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "获取指定用户")
    @RequestMapping(value = {"/user/{userId}"}, method = {RequestMethod.GET})
    public Responses<SysUser> getUser(@PathVariable(required = true) String userId, HttpServletRequest request) {
        SysUser sysUser = sysUserServiceImpl.getCacheById(userId);
        return responses(HttpStatus.OK, sysUser);
    }

    /**
     * 获取所有用户
     *
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "获取所有用户")
    @RequestMapping(value = {"/user"}, method = {RequestMethod.GET})
    public Responses<List<SysUser>> list(HttpServletRequest request, HttpServletResponse response) {
        List<SysUser> sysUserList = sysUserServiceImpl.selectAll();
        return responses(HttpStatus.OK, sysUserList);
    }

    /**
     * 删除用户
     *
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "删除用户")
    @RequestMapping(value = {"/user/{userId}"}, method = {RequestMethod.DELETE})
    public Responses<Void> delete(@PathVariable(required = true) String userId, HttpServletRequest request, HttpServletResponse response) {
        int num = sysUserServiceImpl.deleteByPrimaryKey(userId);
        //response.setStatus(HttpStatus.NO_CONTENT.value());
        if(num==0){
            return responses(HttpStatus.NOT_FOUND.value(),"用户信息不存在");
        }
        return responses(HttpStatus.OK);
    }

    @Resources(auth = AuthTypeEnum.OPEN,description = "维护用户")
    @RequestMapping(value = {"/user"}, method = {RequestMethod.PUT})
    public Responses<SysUser> put(@RequestBody(required = true) @Validated SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        sysUserServiceImpl.updateByPrimaryKeySelective(sysUser);
        return responses(HttpStatus.OK,sysUserServiceImpl.selectByPrimaryKey(sysUser.getUserId()));
    }

    @Resources(auth = AuthTypeEnum.OPEN,description = "新增用户")
    @RequestMapping(value = {"/user"}, method = {RequestMethod.POST})
    public Responses<Void> post(@RequestBody(required = true) SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        sysUserServiceImpl.insertSelective(sysUser);
        return responses(HttpStatus.OK);
    }

    @Resources(auth = AuthTypeEnum.OPEN, description = "用户和角色")
    @RequestMapping(value = {"/sysUserRole"}, method = {RequestMethod.POST})
    public Responses<Void> sysUserRolePost(@RequestBody(required = true) SysUserRole sysUserRole, HttpServletRequest request, HttpServletResponse response) {
        sysUserRoleService.insertSelective(sysUserRole);
        return responses(HttpStatus.OK);
    }


    @Resources(auth = AuthTypeEnum.OPEN, description = "用户和工作组")
    @RequestMapping(value = {"/sysUserPersim"}, method = {RequestMethod.POST})
    public Responses<Void> sysUserPersimPost(@RequestBody(required = true) SysUserPermission sysUserPermission, HttpServletRequest request, HttpServletResponse response) {
        sysUserPermissionService.insertSelective(sysUserPermission);
        return responses(HttpStatus.OK);
    }


    @Resources(auth = AuthTypeEnum.OPEN, description = "用户和角色")
    @RequestMapping(value = {"/sysUserRole"}, method = {RequestMethod.PUT})
    public Responses<Void> sysUserRolePut(@RequestBody(required = true) SysUserRole sysUserRole, HttpServletRequest request, HttpServletResponse response) {
        sysUserRoleService.updateByPrimaryKeySelective(sysUserRole);
        return responses(HttpStatus.OK);
    }


    @Resources(auth = AuthTypeEnum.OPEN, description = "用户和工作组")
    @RequestMapping(value = {"/sysUserPersim"}, method = {RequestMethod.PUT})
    public Responses<Void> sysUserPersimPut(@RequestBody(required = true) SysUserPermission sysUserPermission, HttpServletRequest request, HttpServletResponse response) {
        sysUserPermissionService.updateByPrimaryKeySelective(sysUserPermission);
        return responses(HttpStatus.OK);
    }

    @Resources(auth = AuthTypeEnum.OPEN, description = "用户和角色")
    @RequestMapping(value = {"/sysUserRole"}, method = {RequestMethod.DELETE})
    public Responses<Void> sysUserRoleDel(@RequestBody(required = true) SysUserRole sysUserRole, HttpServletRequest request, HttpServletResponse response) {
        sysUserRoleService.deleteSelective(sysUserRole);
        return responses(HttpStatus.OK);
    }


    @Resources(auth = AuthTypeEnum.OPEN, description = "用户和工作组")
    @RequestMapping(value = {"/sysUserPersim"}, method = {RequestMethod.DELETE})
    public Responses<Void> sysUserPersimDel(@RequestBody(required = true) SysUserPermission sysUserPermission, HttpServletRequest request, HttpServletResponse response) {
        sysUserPermissionService.deleteSelective(sysUserPermission);
        return responses(HttpStatus.OK);
    }

}
