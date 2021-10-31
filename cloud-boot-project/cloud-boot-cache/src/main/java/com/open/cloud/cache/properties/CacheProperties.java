package com.open.cloud.cache.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloud.cache")
public class CacheProperties {

    @Setter
    @Getter
    private long millis;
}