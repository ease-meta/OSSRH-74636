package com.open.cloud.workflow.framework.config;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.open.cloud.workflow.framework.entity.SysConfig;
import com.open.cloud.workflow.framework.serviceImpl.SysConfigServiceImpl;

@Service("config")
public class ConfigService {
	@Autowired
	private SysConfigServiceImpl configService;

	/**
	 * 根据键名查询参数配置信息
	 * 
	 * @param configKey
	 *            参数名称
	 * @return 参数键值
	 */
	public String getKey(String configKey) {
		SysConfig retConfig = configService.selectConfigByKey(configKey);
		return Objects.nonNull(retConfig) ? retConfig.getConfigValue() : "";
	}
}