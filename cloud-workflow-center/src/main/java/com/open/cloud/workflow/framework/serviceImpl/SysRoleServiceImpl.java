package com.open.cloud.workflow.framework.serviceImpl;


import com.open.cloud.workflow.framework.entity.SysRole;
import com.open.cloud.workflow.framework.mapper.SysRoleMapper;
import com.open.cloud.workflow.framework.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Service
@Slf4j
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    public SysRole getCacheById(String roleId) {
        if (log.isDebugEnabled()) {
            log.debug("通过数据库获取用户信息");
        }
        return sysRoleMapper.selectByPrimaryKey(roleId);
    }
}
