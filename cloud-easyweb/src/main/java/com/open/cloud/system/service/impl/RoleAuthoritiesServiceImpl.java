package com.open.cloud.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.open.cloud.system.dao.RoleAuthoritiesMapper;
import com.open.cloud.system.model.RoleAuthorities;
import com.open.cloud.system.service.RoleAuthoritiesService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Administrator on 2018-12-19 下午 4:39.
 */
@Service
public class RoleAuthoritiesServiceImpl extends ServiceImpl<RoleAuthoritiesMapper, RoleAuthorities> implements RoleAuthoritiesService {

    @Override
    public void deleteTrash() {
        baseMapper.deleteTrash();
    }

    @Override
    public boolean saveBatch(final Collection<RoleAuthorities> entityList) {
        return false;
    }
}
