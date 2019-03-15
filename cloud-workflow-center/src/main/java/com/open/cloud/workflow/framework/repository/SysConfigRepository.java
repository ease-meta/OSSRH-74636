/**
 * <p>Title: SysConfig.java</p>  
 * <p>Description: </p> 
 * @author leijian
 * @date 2019年3月14日
 * @version 1.0
 */
package com.open.cloud.workflow.framework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.open.cloud.workflow.framework.entity.SysConfig;

/**
 * @author leijian
 * @date 2019年3月14日
 */
@Repository
public interface SysConfigRepository extends JpaRepository<SysConfig, Integer> {

    SysConfig findSysConfigByConfigKey(final String configKey);
}
