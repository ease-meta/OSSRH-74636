package com.open.cloud.eureka.gateway.model;

import java.util.*;

public class BaseAndTempLimiter
{
    private List<LimiterDefinition> baseLimiter;
    private List<LimiterDefinition> tempLimiter;
    
    public List<LimiterDefinition> getBaseLimiter() {
        return this.baseLimiter;
    }
    
    public List<LimiterDefinition> getTempLimiter() {
        return this.tempLimiter;
    }
    
    public void setBaseLimiter(final List<LimiterDefinition> baseLimiter) {
        this.baseLimiter = baseLimiter;
    }
    
    public void setTempLimiter(final List<LimiterDefinition> tempLimiter) {
        this.tempLimiter = tempLimiter;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseAndTempLimiter)) {
            return false;
        }
        final BaseAndTempLimiter other = (BaseAndTempLimiter)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$baseLimiter = this.getBaseLimiter();
        final Object other$baseLimiter = other.getBaseLimiter();
        Label_0065: {
            if (this$baseLimiter == null) {
                if (other$baseLimiter == null) {
                    break Label_0065;
                }
            }
            else if (this$baseLimiter.equals(other$baseLimiter)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$tempLimiter = this.getTempLimiter();
        final Object other$tempLimiter = other.getTempLimiter();
        if (this$tempLimiter == null) {
            if (other$tempLimiter == null) {
                return true;
            }
        }
        else if (this$tempLimiter.equals(other$tempLimiter)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof BaseAndTempLimiter;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $baseLimiter = this.getBaseLimiter();
        result = result * 59 + (($baseLimiter == null) ? 43 : $baseLimiter.hashCode());
        final Object $tempLimiter = this.getTempLimiter();
        result = result * 59 + (($tempLimiter == null) ? 43 : $tempLimiter.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "BaseAndTempLimiter(baseLimiter=" + this.getBaseLimiter() + ", tempLimiter=" + this.getTempLimiter() + ")";
    }
    
    public BaseAndTempLimiter() {
        this.baseLimiter = new ArrayList<LimiterDefinition>();
        this.tempLimiter = new ArrayList<LimiterDefinition>();
    }
}
