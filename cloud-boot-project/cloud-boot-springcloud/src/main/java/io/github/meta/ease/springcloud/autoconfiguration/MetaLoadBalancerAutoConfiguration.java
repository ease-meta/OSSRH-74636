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
package io.github.meta.ease.springcloud.autoconfiguration;

import io.github.meta.ease.springcloud.MetaLoadBalancerClientSpecification;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClientsProperties;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClientSpecification;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.loadbalancer.config.LoadBalancerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/17 21:32
 */
@Configuration(proxyBeanMethods = false)
@LoadBalancerClients
@EnableConfigurationProperties(LoadBalancerClientsProperties.class)
@AutoConfigureBefore({LoadBalancerAutoConfiguration.class})
public class MetaLoadBalancerAutoConfiguration {

	/**
	 * @return
	 * @see org.springframework.cloud.context.named.NamedContextFactory#createContext(String)
	 * @see org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory
	 * @see org.springframework.cloud.loadbalancer.config.LoadBalancerAutoConfiguration#configurations
	 */
	@Bean
	public LoadBalancerClientSpecification metaLoadBalancerClientSpecification() {
		LoadBalancerClientSpecification loadBalancerClientSpecification = new LoadBalancerClientSpecification();
		loadBalancerClientSpecification.setName(
				"default." + MetaLoadBalancerClientSpecification.class.getName());
		loadBalancerClientSpecification
				.setConfiguration(MetaLoadBalancerClientSpecification.class.getClasses());
		return loadBalancerClientSpecification;
	}

}
