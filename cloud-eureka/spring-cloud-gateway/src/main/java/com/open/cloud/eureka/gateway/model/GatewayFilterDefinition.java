package com.open.cloud.eureka.gateway.model;

import java.util.*;

public class GatewayFilterDefinition
{
    private String name;
    private Map<String, String> args;
    
    public GatewayFilterDefinition() {
        this.args = new LinkedHashMap<String, String>();
    }
    
    public String getName() {
        return this.name;
    }
    
    public GatewayFilterDefinition setName(final String name) {
        this.name = name;
        return this;
    }
    
    public Map<String, String> getArgs() {
        return this.args;
    }
    
    public GatewayFilterDefinition setArgs(final Map<String, String> args) {
        this.args = args;
        return this;
    }
}
