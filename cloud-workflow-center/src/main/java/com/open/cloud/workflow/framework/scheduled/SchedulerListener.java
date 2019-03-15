package com.open.cloud.workflow.framework.scheduled;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.open.cloud.workflow.framework.config.EnvironmentConfiguration;

/**
 * @author leijian
 */
@Configuration
public class SchedulerListener {

	@Autowired
	JobFactory jobFactory;
	@Autowired
	EnvironmentConfiguration environmentConfiguration;

	@Bean(name = "schedulerFactoryBean")
	public SchedulerFactoryBean schedulerFactory() {
		SchedulerFactoryBean bean = new SchedulerFactoryBean();
		bean.setJobFactory(jobFactory);
		bean.setConfigLocation(new FileSystemResource(
				String.format("%s/quartz.properties", environmentConfiguration.getSystemPath())));
		return bean;
	}

}