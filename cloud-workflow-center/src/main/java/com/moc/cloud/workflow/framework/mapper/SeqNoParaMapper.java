package com.moc.cloud.workflow.framework.mapper;

import com.moc.cloud.workflow.common.utils.BaseMapper;
import com.moc.cloud.workflow.framework.entity.SeqNoPara;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2019-01-12
 */
public interface SeqNoParaMapper extends BaseMapper<SeqNoPara> {

    @Override
    List<SeqNoPara> selectAll();

    List<SeqNoPara> findAll();
}
