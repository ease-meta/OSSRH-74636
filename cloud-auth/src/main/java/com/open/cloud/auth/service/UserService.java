package com.open.cloud.auth.service;

import com.open.cloud.auth.entity.UserPo;
/**
 * @author Leijian
 * @date   2020/2/8
 */
public interface UserService {

    /**
     * 添加新用户
     *
     * username 唯一， 默认 USER 权限
     */
    void insert(UserPo userPo);

    /**
     * 查询用户信息
     * @param username 账号
     * @return UserEntity
     */
    UserPo getByUsername(String username);

}