package com.moc.cloud.workflow.framework.serviceImpl;


import com.moc.cloud.workflow.framework.entity.ReprocdefFlow;
import com.moc.cloud.workflow.framework.mapper.ReprocdefFlowMapper;
import com.moc.cloud.workflow.framework.service.ReprocdefFlowService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 流程定义节点信息 服务实现类
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
@Service
public class ReprocdefFlowServiceImpl extends ServiceImpl<ReprocdefFlowMapper, ReprocdefFlow> implements ReprocdefFlowService {

    @Resource
    ReprocdefFlowMapper reprocdefFlowMapper;

    public List<ReprocdefFlow> seletFlowsByKey(String key) {
        return reprocdefFlowMapper.seletFlowsByKey(key);
    }

    public Map<String, ReprocdefFlow> seletFlowsByKeyForMap(String key) {
        List<ReprocdefFlow> reprocdefFlowList = reprocdefFlowMapper.seletFlowsByKey(key);
        List<String> sids = new ArrayList<>();
        Map<String, ReprocdefFlow> map = reprocdefFlowList.stream().filter(Objects::nonNull).filter(v -> {
            boolean flag = !sids.contains(v.getSid());
            sids.add(v.getSid());
            return flag;
        }).collect(Collectors.toMap(ReprocdefFlow::getSid, ReprocdefFlow -> ReprocdefFlow));
        return map;
    }
}
