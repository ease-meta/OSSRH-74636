package com.open.cloud.workflow.framework.serviceImpl;


import com.open.cloud.workflow.framework.entity.ActHiProcinst;
import com.open.cloud.workflow.framework.mapper.ActHiProcinstMapper;
import com.open.cloud.workflow.framework.service.ActHiProcinstService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 流程实例ID 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Service
public class ActHiProcinstServiceImpl extends ServiceImpl<ActHiProcinstMapper, ActHiProcinst> implements ActHiProcinstService {

    @Resource
    ActHiProcinstMapper actHiProcinstMapper;

    public List<ActHiProcinst> selectByprocDefKey(String procDefKey) {
        return actHiProcinstMapper.selectByprocDefKey(procDefKey);
    }

    public ActHiProcinst selectByBusinessKey(String businessKey) {
        return actHiProcinstMapper.selectByBusinessKey(businessKey);
    }

    public int complete(ActHiProcinst actHiProcinst) {
        return actHiProcinstMapper.complete(actHiProcinst);
    }


}
