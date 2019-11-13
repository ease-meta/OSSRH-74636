package com.batch.cloud.workflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {XADataSourceAutoConfiguration.class})
@EnableCaching
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@MapperScan(basePackages = {"com.batch.cloud.workflow", "com.open.cloud.workflow.framework.mapper", "com.open.cloud.workflow.framework.drools.dao"})
public class SpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchApplication.class, args);
    }
}