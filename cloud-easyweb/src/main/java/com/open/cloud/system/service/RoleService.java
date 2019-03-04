package com.open.cloud.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.open.cloud.system.model.Role;

public interface RoleService extends IService<Role> {

    Integer[] getRoleIds(String userId);

}
