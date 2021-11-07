package io.github.meta.ease.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author guankai created on 2018/11/16.
 */
@SpringBootApplication
@EnableCaching
public class GatewayApplicationTest {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(GatewayApplicationTest.class, args);
        configurableApplicationContext.start();
    }
}
