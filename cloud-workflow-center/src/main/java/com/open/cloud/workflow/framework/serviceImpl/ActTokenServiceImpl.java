/**
 * <p>Title: ActTokenServiceImpl.java</p>  
 * <p>Description: </p> 
 * @author leijian
 * @date 2019年2月26日
 * @version 1.0
 */
package com.open.cloud.workflow.framework.serviceImpl;

import org.springframework.stereotype.Service;

import com.open.cloud.workflow.framework.entity.ActToken;
import com.open.cloud.workflow.framework.mapper.ActTokenMapper;
import com.open.cloud.workflow.framework.service.ActTokenService;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author leijian
 * @date 2019年2月26日
 */
@Service
public class ActTokenServiceImpl  extends ServiceImpl<ActTokenMapper, ActToken> implements ActTokenService{

	@Resource
	ActTokenMapper actTokenMapper;
	/**
	 * @param string
	 * @return
	 */
	public boolean existsById(String string) {
		return Objects.isNull(actTokenMapper.selectByPrimaryKey(string));
	}

	/**
	 * @param tokenDTO
	 */
	public void save(ActToken tokenDTO) {
		actTokenMapper.insert(tokenDTO);
		
	}

}
