/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.meta.ease.test.web.mybatis;

import com.github.pagehelper.autoconfigure.PageHelperProperties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;
import java.util.Properties;

/**
 * 自定注入分页插件
 *
 * @author liuzh
 */
@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties(PageHelperProperties.class)
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@Lazy(false)
public class MybatisInterceptorAutoConfiguration implements InitializingBean {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private PageHelperProperties properties;

    /**
     * 接受分页插件额外的属性
     *
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = PageHelperProperties.PAGEHELPER_PREFIX)
    public Properties pageHelperProperties() {
        return new Properties();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Interceptor interceptor = new MybatisInterceptor();
        Properties properties = new Properties();
        //先把一般方式配置的属性放进去
        properties.putAll(pageHelperProperties());
        //在把特殊配置放进去，由于close-conn 利用上面方式时，属性名就是 close-conn 而不是 closeConn，所以需要额外的一步
        properties.putAll(this.properties.getProperties());
        interceptor.setProperties(properties);
        for (SqlSessionFactory sqlSessionFactory : sqlSessionFactoryList) {
            org.apache.ibatis.session.Configuration configuration = sqlSessionFactory
                    .getConfiguration();
            if (!containsInterceptor(configuration, interceptor)) {
                configuration.addInterceptor(interceptor);
            }
        }
    }

    /**
     * 是否已经存在相同的拦截器
     *
     * @param configuration
     * @param interceptor
     * @return
     */
    private boolean containsInterceptor(org.apache.ibatis.session.Configuration configuration,
                                        Interceptor interceptor) {
        try {
            // getInterceptors since 3.2.2
            return configuration.getInterceptors().contains(interceptor);
        } catch (Exception e) {
            return false;
        }
    }

}
