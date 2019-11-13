package com.open.cloud.workflow;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author leijian
 * @date 2019/1/1
 */
@SpringBootApplication(exclude = {XADataSourceAutoConfiguration.class})
@EnableCaching
@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@MapperScan(basePackages = {"com.open.cloud.workflow.framework.mapper","com.open.cloud.workflow.framework.drools.dao"})
public class SpringbootApplicationBranchWorkflow extends SpringBootServletInitializer {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringbootApplicationBranchWorkflow.class).run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplicationBranchWorkflow.class);
    }

}
