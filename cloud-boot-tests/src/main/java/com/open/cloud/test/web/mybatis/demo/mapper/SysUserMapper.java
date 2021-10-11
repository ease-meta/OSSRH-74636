package com.open.cloud.test.web.mybatis.demo.mapper;


import com.open.cloud.mybatis.BaseMapper;
import com.open.cloud.test.web.mybatis.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2021-10-11
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("SELECT * FROM sys_user")
    public List<SysUser> selectByPage();
}
