package com.open.cloud.spring.mybatis.service.impl;

import com.open.cloud.spring.mybatis.entity.StriaFlow;
import com.open.cloud.spring.mybatis.dao.StriaFlowDao;
import com.open.cloud.spring.mybatis.service.StriaFlowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Stria流程定义(StriaFlow)表服务实现类
 *
 * @author makejava
 * @since 2020-04-04 03:38:59
 */
@Service("striaFlowService")
public class StriaFlowServiceImpl implements StriaFlowService {
    @Resource
    private StriaFlowDao striaFlowDao;


    @Override
    public List<StriaFlow> queryAll() {
        return striaFlowDao.queryAll();
    }
}