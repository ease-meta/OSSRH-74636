package com.open.cloud.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.open.cloud.system.model.User;

public interface UserService extends IService<User> {

    User getByUsername(String username);

}
