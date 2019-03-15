package com.open.cloud.workflow.framework.serviceImpl;


import com.open.cloud.workflow.framework.entity.SysUser;
import com.open.cloud.workflow.framework.mapper.SysUserMapper;
import com.open.cloud.workflow.framework.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public SysUser getCacheById(String userId) {
        if (log.isDebugEnabled()) {
            log.debug("通过数据库获取用户信息");
        }

        return sysUserMapper.selectByPrimaryKey(userId);
    }

    public SysUser getCacheByUsername(String username) {
        if (log.isDebugEnabled()) {
            log.debug("通过数据库获取用户信息");
        }
        return sysUserMapper.selectByUsername(username);
    }


    public List<HashMap> getUserByRole(String roleId) {
        HashMap hashMap = new HashMap();
        hashMap.put("roleId", roleId);
        return sysUserMapper.selectUserByRole(hashMap);
    }

    public SysUser findUserByName(String username) {
        return sysUserMapper.selectByPrimaryKey(username);
    }

}
