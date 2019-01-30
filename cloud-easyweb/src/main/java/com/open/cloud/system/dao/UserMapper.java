package com.open.cloud.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.open.cloud.system.model.User;

public interface UserMapper extends BaseMapper<User> {

    User getByUsername(String username);
}
