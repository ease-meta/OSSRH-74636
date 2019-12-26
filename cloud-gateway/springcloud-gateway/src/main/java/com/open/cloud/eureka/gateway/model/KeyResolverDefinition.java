package com.open.cloud.eureka.gateway.model;

import java.util.*;

public class KeyResolverDefinition
{
    private HashMap<String, LimiterFilterDefinition> keyResolvers;
    
    public HashMap<String, LimiterFilterDefinition> getKeyResolvers() {
        return this.keyResolvers;
    }
    
    public void setKeyResolvers(final HashMap<String, LimiterFilterDefinition> keyResolvers) {
        this.keyResolvers = keyResolvers;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof KeyResolverDefinition)) {
            return false;
        }
        final KeyResolverDefinition other = (KeyResolverDefinition)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$keyResolvers = this.getKeyResolvers();
        final Object other$keyResolvers = other.getKeyResolvers();
        if (this$keyResolvers == null) {
            if (other$keyResolvers == null) {
                return true;
            }
        }
        else if (this$keyResolvers.equals(other$keyResolvers)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof KeyResolverDefinition;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $keyResolvers = this.getKeyResolvers();
        result = result * 59 + (($keyResolvers == null) ? 43 : $keyResolvers.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "KeyResolverDefinition(keyResolvers=" + this.getKeyResolvers() + ")";
    }
    
    public KeyResolverDefinition() {
        this.keyResolvers = new HashMap<String, LimiterFilterDefinition>();
    }
}
