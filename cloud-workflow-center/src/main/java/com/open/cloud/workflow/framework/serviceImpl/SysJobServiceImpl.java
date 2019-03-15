package com.open.cloud.workflow.framework.serviceImpl;


import com.open.cloud.workflow.framework.entity.SysJob;
import com.open.cloud.workflow.framework.mapper.SysJobMapper;
import com.open.cloud.workflow.framework.service.SysJobService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Service
public class SysJobServiceImpl implements SysJobService {

    @Resource
    private SysJobMapper sysJobMapper;

    public List<SysJob> list() {
        return sysJobMapper.selectAll();
    }

    public void updateById(SysJob sysJob) {
        sysJobMapper.updateByPrimaryKey(sysJob);
    }
}
