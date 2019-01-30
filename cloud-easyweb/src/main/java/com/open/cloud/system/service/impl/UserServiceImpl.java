package com.open.cloud.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.open.cloud.system.dao.UserMapper;
import com.open.cloud.system.model.User;
import com.open.cloud.system.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

}
