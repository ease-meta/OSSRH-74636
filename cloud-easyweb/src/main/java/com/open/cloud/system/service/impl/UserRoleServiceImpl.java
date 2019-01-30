package com.open.cloud.system.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.open.cloud.system.dao.UserMapper;
import com.open.cloud.system.dao.UserRoleMapper;
import com.open.cloud.system.model.User;
import com.open.cloud.system.model.UserRole;
import com.open.cloud.system.service.UserRoleService;
import com.open.cloud.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018-12-19 下午 4:10.
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
