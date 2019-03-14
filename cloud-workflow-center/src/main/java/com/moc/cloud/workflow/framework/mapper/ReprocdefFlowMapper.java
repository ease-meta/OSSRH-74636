package com.moc.cloud.workflow.framework.mapper;

import com.moc.cloud.workflow.common.utils.BaseMapper;
import com.moc.cloud.workflow.framework.entity.ReprocdefFlow;

import java.util.List;

/**
 * <p>
 * 流程定义节点信息 Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
public interface ReprocdefFlowMapper extends BaseMapper<ReprocdefFlow> {
    /**
     *
     * @return
     */
    int selectCountTest();


    /**
     *
     * @return
     */
    List<ReprocdefFlow> seletFlowsByKey(String key);
}
