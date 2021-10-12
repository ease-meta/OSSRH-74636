package com.open.cloud.test.web.mybatis.demo.mapper;


import com.open.cloud.mybatis.BaseMapper;
import com.open.cloud.test.web.mybatis.demo.entity.SysUser;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
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
    /*
 这个一个接口，但不需要实现它，用于 函数与SQL语句 的映射
 * */

    @Insert("insert into sys_user(user_id,username,user_name,user_password,status) values(#{userId},#{username},#{userName},#{userPassword},#{status})")
    public void insertT(SysUser user);

    @Delete("delete from sys_user where user_id=#{userId}")
    public void deleteById(String userId);

    @Delete("delete from sys_user where user_id=#{userId}")
    public void deleteByPage(HashMap hashMap);

    @Update("update sys_user set username=#{username},status=#{status} where user_id=#{userId}")
    public void updateTByPage(SysUser user);

    @Select("select * from sys_user where user_id=#{userId}")
    public SysUser getUser(int id);

    @Select("select * from sys_user")
    public List<SysUser> getAllUsers();
}
