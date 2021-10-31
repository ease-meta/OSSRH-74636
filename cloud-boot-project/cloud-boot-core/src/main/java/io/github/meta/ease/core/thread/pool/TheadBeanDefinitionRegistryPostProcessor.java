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
package io.github.meta.ease.core.thread.pool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Thead bean definition registry post processor.
 *
 * @author Leijian
 * @date 2020 /5/23 22:27
 */
@Slf4j
public class TheadBeanDefinitionRegistryPostProcessor implements
        BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        //默认配置
        UserThreadPool defUserThreadPool = new UserThreadPool();
        Set<UserThreadPool> thead = orderTheadPool(readJsonFromClassPath());
        thead.add(defUserThreadPool);
        thead.forEach(item -> {
            UserThreadPoolManager.registerUserThread(item.getThreadPoolName(), item);
            item.getService().forEach(s ->
                    UserThreadPoolManager.registerUserThread(s, item)
            );
            log.info("[{}]-Thread pool created successfully!", item.getThreadPoolName());
        });
    }

    /**
     * Order thead pool set.
     *
     * @return the set
     */
    private Set<UserThreadPool> orderTheadPool(List<UserThreadPool> userThreadPools) {
        //排序后  放入set order大的在前 order小的失效
        List<UserThreadPool> finalOrderList = userThreadPools.stream().sorted(Comparator.comparing(UserThreadPool::getOrder).reversed()).collect(Collectors.toList());
        return new HashSet<>(finalOrderList);
    }

    /**
     * Read json from class path list.
     *
     * @return the list
     */
    private List<UserThreadPool> readJsonFromClassPath() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<UserThreadPool> theadPoolPos = new ArrayList<>();
        try {
            Resource[] resources = resolver.getResources("classpath*:META-INF/config/thread-pool.json");
            for (Resource resource : resources) {
                if (resource.exists()) {
                    try (InputStream inputStream = resource.getInputStream()) {
                        List<JSONObject> c = JSON.parseObject(inputStream, StandardCharsets.UTF_8, List.class,
                                // 自动关闭流
                                Feature.AutoCloseSource,
                                // 允许注释
                                Feature.AllowComment,
                                // 允许单引号
                                Feature.AllowSingleQuotes,
                                // 使用 Big decimal
                                Feature.UseBigDecimal);
                        c.forEach(item -> {
                            UserThreadPool theadPoolPo = JSONObject.toJavaObject(item, UserThreadPool.class);
                            theadPoolPos.add(theadPoolPo);
                        });
                    }
                }
            }
        } catch (IOException e) {
            log.error("create thread pool exception{},{}", e.getMessage(), e);
        }
        return theadPoolPos;
    }

    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        // Do nothing
    }
}
