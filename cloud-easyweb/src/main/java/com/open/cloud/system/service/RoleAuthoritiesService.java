package com.open.cloud.system.service;

import com.baomidou.mybatisplus.service.IService;
import com.open.cloud.system.model.RoleAuthorities;
import com.open.cloud.system.model.User;

/**
 * Created by Administrator on 2018-12-19 下午 4:38.
 */
public interface RoleAuthoritiesService extends IService<RoleAuthorities> {

    void deleteTrash();
}
