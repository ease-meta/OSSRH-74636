package com.open.cloud.dubbo.provider.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author Leijian
 * @date 2020/4/20
 */
@Configuration
public class TransactionConfig {

	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
}
