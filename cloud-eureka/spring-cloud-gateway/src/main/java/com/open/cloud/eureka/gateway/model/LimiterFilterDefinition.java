package com.open.cloud.eureka.gateway.model;

import java.util.*;

public class LimiterFilterDefinition
{
    private HashMap<String, RateConfig> limiterInfo;
    
    public HashMap<String, RateConfig> getLimiterInfo() {
        return this.limiterInfo;
    }
    
    public void setLimiterInfo(final HashMap<String, RateConfig> limiterInfo) {
        this.limiterInfo = limiterInfo;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LimiterFilterDefinition)) {
            return false;
        }
        final LimiterFilterDefinition other = (LimiterFilterDefinition)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$limiterInfo = this.getLimiterInfo();
        final Object other$limiterInfo = other.getLimiterInfo();
        if (this$limiterInfo == null) {
            if (other$limiterInfo == null) {
                return true;
            }
        }
        else if (this$limiterInfo.equals(other$limiterInfo)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof LimiterFilterDefinition;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $limiterInfo = this.getLimiterInfo();
        result = result * 59 + (($limiterInfo == null) ? 43 : $limiterInfo.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "LimiterFilterDefinition(limiterInfo=" + this.getLimiterInfo() + ")";
    }
    
    public LimiterFilterDefinition() {
        this.limiterInfo = new HashMap<String, RateConfig>();
    }
}
