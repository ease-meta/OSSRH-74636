package com.open.cloud.workflow.framework.mapper;

import com.open.cloud.workflow.common.utils.BaseMapper;
import com.open.cloud.workflow.framework.entity.ReprocdefFlowLines;

import java.util.List;

/**
 * <p>
 * 流程连接线定义 Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
public interface ReprocdefFlowLinesMapper extends BaseMapper<ReprocdefFlowLines> {

    List<ReprocdefFlowLines> seletFlowsLinesByKey(String key);
}
