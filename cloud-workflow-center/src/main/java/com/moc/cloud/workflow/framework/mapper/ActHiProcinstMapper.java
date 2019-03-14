package com.moc.cloud.workflow.framework.mapper;


import com.moc.cloud.workflow.common.utils.BaseMapper;
import com.moc.cloud.workflow.framework.entity.ActHiProcinst;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 流程实例ID Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
public interface ActHiProcinstMapper extends BaseMapper<ActHiProcinst> {

    ActHiProcinst selectByBusinessKey(String businessKey);

    @Update("update dc_act_hi_procinst set END_TIME=#{endTime,jdbcType=TIMESTAMP},END_ACT_ID=#{endActId},DELETE_REASON=#{deleteReason},DURATION=TIMESTAMPDIFF(SECOND,START_TIME,#{endTime,jdbcType=TIMESTAMP}) where PROC_INST_ID=#{procInstId}")
    int complete(ActHiProcinst actHiProcinst);

    @Select("select * from dc_act_hi_procinst where PROC_DEF_KEY=#{procDefKey} and END_TIME IS NULL")
    List<ActHiProcinst> selectByprocDefKey(String procDefKey);

}
