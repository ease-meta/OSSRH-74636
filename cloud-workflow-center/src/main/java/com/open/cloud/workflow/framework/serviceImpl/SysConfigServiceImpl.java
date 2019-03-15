/**
 * <p>Title: SysConfigServiceImpl.java</p>  
 * <p>Description: </p> 
 * @author leijian
 * @date 2019年3月14日
 * @version 1.0
 */
package com.open.cloud.workflow.framework.serviceImpl;

import com.open.cloud.workflow.framework.entity.SysConfig;
import com.open.cloud.workflow.framework.repository.SysConfigRepository;
import com.open.cloud.workflow.framework.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author leijian
 * @date 2019年3月14日
 */
@Service
public class SysConfigServiceImpl implements SysConfigService{

    @Autowired
    SysConfigRepository sysConfigRepository;


    public SysConfig selectConfigByKey(final String configKey) {
        return  sysConfigRepository.findSysConfigByConfigKey(configKey);
    }
}
