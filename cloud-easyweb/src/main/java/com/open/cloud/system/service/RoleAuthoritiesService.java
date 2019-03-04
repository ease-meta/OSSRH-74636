package com.open.cloud.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.open.cloud.system.model.RoleAuthorities;

/**
 * Created by Administrator on 2018-12-19 下午 4:38.
 */
public interface RoleAuthoritiesService extends IService<RoleAuthorities> {

    void deleteTrash();
}
