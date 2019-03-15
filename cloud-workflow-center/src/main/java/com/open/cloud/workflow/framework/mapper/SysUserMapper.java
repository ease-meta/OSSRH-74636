package com.open.cloud.workflow.framework.mapper;

import com.open.cloud.workflow.common.utils.BaseMapper;
import com.open.cloud.workflow.framework.entity.SysUser;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
public interface SysUserMapper extends BaseMapper<SysUser> {


    List<HashMap> selectUserByRole(HashMap hashMap);

    SysUser selectByUsername(String username);
}
