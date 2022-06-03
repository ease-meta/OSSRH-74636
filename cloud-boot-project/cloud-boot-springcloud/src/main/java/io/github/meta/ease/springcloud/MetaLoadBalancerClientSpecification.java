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
package io.github.meta.ease.springcloud;

import io.github.meta.ease.logging.BizLogger;
import io.github.meta.ease.logging.BizLoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ConditionalOnBlockingDiscoveryEnabled;
import org.springframework.cloud.client.ConditionalOnDiscoveryEnabled;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.loadbalancer.config.LoadBalancerZoneConfig;
import org.springframework.cloud.loadbalancer.core.*;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/17 21:30
 * @see org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientConfiguration
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnDiscoveryEnabled
public class MetaLoadBalancerClientSpecification {

    private static final BizLogger bizLogger                                = BizLoggerFactory
                                                                                .getLogger(MetaLoadBalancerClientSpecification.class);

    private static final int       REACTIVE_SERVICE_INSTANCE_SUPPLIER_ORDER = 193827465 - 5;

    @Configuration(proxyBeanMethods = false)
    @ConditionalOnBlockingDiscoveryEnabled
    @Order(REACTIVE_SERVICE_INSTANCE_SUPPLIER_ORDER + 1)
    public static class BlockingSupportConfiguration {

        @Bean
        public ReactorLoadBalancer<ServiceInstance> reactorServiceInstanceLoadBalancer(Environment environment,
                                                                                       LoadBalancerClientFactory loadBalancerClientFactory) {
            String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
            return new RoundRobinLoadBalancer(loadBalancerClientFactory.getLazyProvider(name,
                ServiceInstanceListSupplier.class), name);
        }

        @Bean
        @ConditionalOnBean(DiscoveryClient.class)
        @ConditionalOnMissingBean
        @ConditionalOnProperty(value = "spring.cloud.loadbalancer.configurations", havingValue = "zone-preference")
        public ServiceInstanceListSupplier zonePreferenceDiscoveryClientServiceInstanceListSupplier(
                ConfigurableApplicationContext context) {
            ServiceInstanceListSupplierBuilder serviceInstanceListSupplierBuilder = ServiceInstanceListSupplier.builder().withBlockingDiscoveryClient().withCaching();
            ServiceInstanceListSupplierBuilder.DelegateCreator delegateCreator = (configurableApplicationContext, serviceInstanceListSupplier) -> new ZonePreferenceServiceInstanceListSupplier(serviceInstanceListSupplier, configurableApplicationContext.getBean(LoadBalancerZoneConfig.class));
            try {
                Field field = serviceInstanceListSupplierBuilder.getClass().getDeclaredField("creators");
                field.setAccessible(true);
                List<ServiceInstanceListSupplierBuilder.DelegateCreator> delegateCreators = (List<ServiceInstanceListSupplierBuilder.DelegateCreator>) field.get(serviceInstanceListSupplierBuilder);
                delegateCreators.add(delegateCreator);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                bizLogger.error("属性creators不存在");
            }
            return serviceInstanceListSupplierBuilder.build(context);
        }
    }
}
