package com.open.cloud.eureka.gateway.model;

import com.fasterxml.jackson.annotation.*;

public class HystrixDefinition
{
    String name;
    @JsonProperty("timeout_in_milliseconds")
    Integer timeoutInMilliseconds;
    @JsonProperty("max_concurrent_requests")
    Integer maxConcurrentRequests;
    
    public String getName() {
        return this.name;
    }
    
    public Integer getTimeoutInMilliseconds() {
        return this.timeoutInMilliseconds;
    }
    
    public Integer getMaxConcurrentRequests() {
        return this.maxConcurrentRequests;
    }
    
    public HystrixDefinition setName(final String name) {
        this.name = name;
        return this;
    }
    
    public HystrixDefinition setTimeoutInMilliseconds(final Integer timeoutInMilliseconds) {
        this.timeoutInMilliseconds = timeoutInMilliseconds;
        return this;
    }
    
    public HystrixDefinition setMaxConcurrentRequests(final Integer maxConcurrentRequests) {
        this.maxConcurrentRequests = maxConcurrentRequests;
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HystrixDefinition)) {
            return false;
        }
        final HystrixDefinition other = (HystrixDefinition)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        Label_0065: {
            if (this$name == null) {
                if (other$name == null) {
                    break Label_0065;
                }
            }
            else if (this$name.equals(other$name)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$timeoutInMilliseconds = this.getTimeoutInMilliseconds();
        final Object other$timeoutInMilliseconds = other.getTimeoutInMilliseconds();
        Label_0102: {
            if (this$timeoutInMilliseconds == null) {
                if (other$timeoutInMilliseconds == null) {
                    break Label_0102;
                }
            }
            else if (this$timeoutInMilliseconds.equals(other$timeoutInMilliseconds)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$maxConcurrentRequests = this.getMaxConcurrentRequests();
        final Object other$maxConcurrentRequests = other.getMaxConcurrentRequests();
        if (this$maxConcurrentRequests == null) {
            if (other$maxConcurrentRequests == null) {
                return true;
            }
        }
        else if (this$maxConcurrentRequests.equals(other$maxConcurrentRequests)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HystrixDefinition;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $timeoutInMilliseconds = this.getTimeoutInMilliseconds();
        result = result * 59 + (($timeoutInMilliseconds == null) ? 43 : $timeoutInMilliseconds.hashCode());
        final Object $maxConcurrentRequests = this.getMaxConcurrentRequests();
        result = result * 59 + (($maxConcurrentRequests == null) ? 43 : $maxConcurrentRequests.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HystrixDefinition(name=" + this.getName() + ", timeoutInMilliseconds=" + this.getTimeoutInMilliseconds() + ", maxConcurrentRequests=" + this.getMaxConcurrentRequests() + ")";
    }
}
