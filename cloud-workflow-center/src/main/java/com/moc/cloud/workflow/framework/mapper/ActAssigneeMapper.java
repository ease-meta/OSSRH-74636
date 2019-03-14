package com.moc.cloud.workflow.framework.mapper;


import com.moc.cloud.workflow.common.utils.BaseMapper;
import com.moc.cloud.workflow.framework.entity.ActAssignee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 流程定义审批人 Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
public interface ActAssigneeMapper extends BaseMapper<ActAssignee> {

    public int deleteBatch(List<ActAssignee> actAssigneeList);


    @Select("SELECT * FROM DC_ACT_ASSIGNEE WHERE  `KEY` = #{key} AND SID = #{sid}")
    public ActAssignee getByKeyAndSid(@Param("key") String key, @Param("sid") String sid);
}
