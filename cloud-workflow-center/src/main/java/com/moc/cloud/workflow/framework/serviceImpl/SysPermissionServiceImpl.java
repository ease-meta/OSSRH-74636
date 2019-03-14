package com.moc.cloud.workflow.framework.serviceImpl;


import com.moc.cloud.workflow.framework.entity.SysPermission;
import com.moc.cloud.workflow.framework.mapper.SysPermissionMapper;
import com.moc.cloud.workflow.framework.service.SysPermissionService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 权限信息表 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;


}
