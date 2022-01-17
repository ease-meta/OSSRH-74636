package io.github.meta.ease.springcloud.autoconfiguration;

import io.github.meta.ease.springcloud.MetaLoadBalancerClientSpecification;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
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
@EnableConfigurationProperties(LoadBalancerProperties.class)
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
        loadBalancerClientSpecification.setName("default." + MetaLoadBalancerClientSpecification.class.getName());
        loadBalancerClientSpecification.setConfiguration(MetaLoadBalancerClientSpecification.class.getClasses());
        return loadBalancerClientSpecification;
    }
}
