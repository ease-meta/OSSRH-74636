package com.open.cloud.workflow.framework.mapper;


import com.open.cloud.workflow.common.utils.BaseMapper;
import com.open.cloud.workflow.framework.entity.ActReProcdef;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 流程定义 Mapper 接口
 * </p>
 *
 * @author leijian
 * @since 2019-01-10
 */
public interface ActReProcdefMapper extends BaseMapper<ActReProcdef> {
    /**
     * @param key
     * @return
     */
    List<ActReProcdef> selectByKey(String key);

    /**
     * @param actReProcdef
     * @return
     */
    int updateByKey(ActReProcdef actReProcdef);


    /**
     * @param id
     * @return
     */
    @Select("SELECT * FROM DC_ACT_RE_PROCDEF WHERE  `ID` = #{id}")
    ActReProcdef selectById(@Param("id") String id);

}
