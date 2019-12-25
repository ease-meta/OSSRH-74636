package com.open.cloud.eureka.gateway.model;

import java.util.*;

public class LimiterPredicateDefinition
{
    private String name;
    private Map<String, String> args;
    
    public LimiterPredicateDefinition() {
        this.args = new LinkedHashMap<String, String>();
    }
}
