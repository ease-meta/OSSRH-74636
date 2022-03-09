package io.github.meta.ease.alibaba.provider.examples;

import org.springframework.cloud.loadbalancer.config.LoadBalancerZoneConfig;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplierBuilder;
import org.springframework.cloud.loadbalancer.core.ZonePreferenceServiceInstanceListSupplier;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author leijian
 * @version 1.0
 * @date 2022/1/15 14:17
 */
public class ProviderAlibabaApplicationTest {

    public static void main(String[] args) throws URISyntaxException {
       // ApplicationConversionService.getSharedInstance().convert(, );
        String host = new URI("http://127.0.0.1").getHost();
        ServiceInstanceListSupplierBuilder serviceInstanceListSupplierBuilder = ServiceInstanceListSupplier.builder().withDiscoveryClient().withZonePreference().withCaching();
        ServiceInstanceListSupplierBuilder.DelegateCreator delegateCreator = (configurableApplicationContext, serviceInstanceListSupplier) -> new ZonePreferenceServiceInstanceListSupplier(serviceInstanceListSupplier, configurableApplicationContext.getBean(LoadBalancerZoneConfig.class));
        //serviceInstanceListSupplierBuilder.build(ConfigurableApplicationContext);
    }
}