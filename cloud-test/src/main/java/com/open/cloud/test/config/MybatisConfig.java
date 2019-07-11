package com.open.cloud.test.config;

import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/7 17:22
 **/
@Configuration
public class MybatisConfig {


    @Bean
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(false);
        configuration.setLazyLoadingEnabled(false);
        configuration.setAggressiveLazyLoading(false);
        configuration.setLogImpl(Log4j2Impl.class);
        configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
        //configuration.addInterceptor(new OracleInterceptor());
        //configuration.addInterceptor(new SelectForUpdateHelper());
        //bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
        // bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath*:META-INF/mybatis/mybatis-config.xml"));
        return factory.getObject();
    }
}
