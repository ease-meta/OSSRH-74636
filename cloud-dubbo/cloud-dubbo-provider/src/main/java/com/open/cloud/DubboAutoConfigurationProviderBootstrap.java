package com.open.cloud;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Dubbo Auto-Configuration Provider Bootstrap
 *
 * @see DefaultDemoService
 * @since 2.7.0
 */
@EnableAutoConfiguration
public class DubboAutoConfigurationProviderBootstrap {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DubboAutoConfigurationProviderBootstrap.class)
                .run(args);
    }
}
