package com.open.cloud.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.open.cloud.system.model.RoleAuthorities;

public interface RoleAuthoritiesMapper extends BaseMapper<RoleAuthorities> {

    int deleteTrash();
}
