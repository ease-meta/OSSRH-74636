/**
 * <p>Title: ResourceMapper.java</p>  
 * <p>Description: </p> 
 * @author leijian
 * @date 2019年1月26日
 * @version 1.0
 */
package com.moc.cloud.workflow.framework.mapper;

import com.moc.cloud.workflow.common.utils.BaseMapper;
import com.moc.cloud.workflow.framework.entity.Resource;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author leijian
 * @date 2019年1月26日
 */
public interface ResourceMapper extends BaseMapper<Resource> {

	/**
	 * @param method
	 * @return
	 */
	@Select("SELECT * FROM dc_act_sys_resource WHERE method=#{method}")
	List<Resource> selectResourceByMethod(@Param("method") String method);

	@Delete("DELETE  FROM  dc_act_sys_resource")
	int deleteAll();

}
