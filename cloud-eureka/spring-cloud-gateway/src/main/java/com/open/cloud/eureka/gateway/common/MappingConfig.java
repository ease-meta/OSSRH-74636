package com.open.cloud.eureka.gateway.common;

import org.springframework.stereotype.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;
import java.util.*;

@Component
@ConfigurationProperties(prefix = "mapping")
@PropertySource({ "classpath:/mapping.properties" })
public class MappingConfig
{
    private static Map<String, String> uri;
    
    public static String getMapping(final String factor) {
        if (MappingConfig.uri.containsKey(factor)) {
            return MappingConfig.uri.get(factor);
        }
        return "";
    }
    
    public Map<String, String> getUri() {
        return MappingConfig.uri;
    }
    
    public static void setUri(final Map<String, String> uri) {
        MappingConfig.uri = uri;
    }
    
    static {
        MappingConfig.uri = new HashMap<String, String>();
    }
}
