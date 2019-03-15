package com.open.cloud.workflow.framework.serviceImpl;


import com.open.cloud.workflow.framework.entity.ReprocdefFlowLines;
import com.open.cloud.workflow.framework.mapper.ReprocdefFlowLinesMapper;
import com.open.cloud.workflow.framework.service.ReprocdefFlowLinesService;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 流程连接线定义 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Service
public class ReprocdefFlowLinesServiceImpl extends ServiceImpl<ReprocdefFlowLinesMapper, ReprocdefFlowLines> implements ReprocdefFlowLinesService {
    /**
     * 按照正排序
     *
     * @param key
     * @return
     */
    public List<ReprocdefFlowLines> seletFlowsLinesByKey(String key) {
        return baseMapper.seletFlowsLinesByKey(key).stream().sorted(Comparator.comparing(ReprocdefFlowLines::getId)).collect(Collectors.toList());
    }
}
