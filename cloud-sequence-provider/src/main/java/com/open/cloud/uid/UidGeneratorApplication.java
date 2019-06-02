package com.open.cloud.uid;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 * @author leijian
 * @date 2019年4月5日
 */
@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@EntityScan(basePackages = {"com.dcits.comet.uid.entity"}, basePackageClasses = Jsr310JpaConverters.class)
public class UidGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(UidGeneratorApplication.class, args);
    }
}

