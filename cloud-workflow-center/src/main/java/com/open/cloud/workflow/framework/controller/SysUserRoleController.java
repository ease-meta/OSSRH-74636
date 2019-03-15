package com.open.cloud.workflow.framework.controller;


import com.open.cloud.workflow.common.enums.AuthTypeEnum;
import com.open.cloud.workflow.common.responses.Responses;
import com.open.cloud.workflow.framework.annotation.Resources;
import com.open.cloud.workflow.framework.config.BaseController;
import com.open.cloud.workflow.framework.entity.SysUserRole;
import com.open.cloud.workflow.framework.serviceImpl.SysUserRoleServiceImpl;
import com.open.cloud.workflow.framework.serviceImpl.SysUserServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 角色用户信息对照表 前端控制器
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@RestController
@ApiIgnore
@Api(tags = "用户角色、用户与工作组")
@RequestMapping(value = "${api.version}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysUserRoleController extends BaseController<SysUserRole> {

    @Autowired
    SysUserRoleServiceImpl sysUserRoleService;

    @Autowired
    SysUserServiceImpl sysUserService;

    /**
     * 根据角色查询角色下的用户
     *
     * @param roleId
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "角色查询")
    @GetMapping(value = {"/roleuser/{roleId}"})
    public Responses<List<HashMap>> getbyroleid(@PathVariable(required = true) String roleId, HttpServletRequest request) {
        List<HashMap> hashMapList = sysUserService.getUserByRole(roleId);
        return responses(HttpStatus.OK, hashMapList);
    }

    /**
     * 根据用户查询用户所属的角色
     *
     * @param userid
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "根据用户查询角色")
    @GetMapping(value = {"/userrole/{userid}"})
    public Responses<List<HashMap>> getbyuseid(@PathVariable(required = true) String userid, HttpServletRequest request) {
        List<HashMap> hashMapList = sysUserService.getUserByRole(userid);
        return responses(HttpStatus.OK, hashMapList);
    }

    /**
     * 更新状态角色和用户状态
     *
     * @param request
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "更新状态角色和用户状态")
    @PatchMapping(value = {"/userrole"})
    public void update(@RequestParam String userId, @RequestParam String roleId, @RequestParam boolean delFlag, HttpServletRequest request) {

    }

    /**
     * 删除角色和用户的对应关系
     *
     * @param userid
     * @param request
     * @param response
     * @return
     */
    @Resources(auth = AuthTypeEnum.OPEN,description = "删除角色和用户的对应关系")
    @DeleteMapping(value = {"/userrole/{userid}"})
    public Responses<List<HashMap>> delete(@PathVariable(required = true) String userid, HttpServletRequest request, HttpServletResponse response) {
        List<HashMap> hashMapList = sysUserService.getUserByRole(userid);
        return responses(HttpStatus.OK, hashMapList);
    }

}
