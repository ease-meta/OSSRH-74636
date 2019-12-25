package com.open.cloud.eureka.gateway.model;

import com.fasterxml.jackson.annotation.*;

public class RibbonDefinition
{
    @JsonProperty("service_name")
    String serviceName;
    @JsonProperty("ribbon_class")
    String ribbonClass;
    @JsonProperty("ribbon_name")
    String ribbonName;
    
    public String getServiceName() {
        return this.serviceName;
    }
    
    public String getRibbonClass() {
        return this.ribbonClass;
    }
    
    public String getRibbonName() {
        return this.ribbonName;
    }
    
    public RibbonDefinition setServiceName(final String serviceName) {
        this.serviceName = serviceName;
        return this;
    }
    
    public RibbonDefinition setRibbonClass(final String ribbonClass) {
        this.ribbonClass = ribbonClass;
        return this;
    }
    
    public RibbonDefinition setRibbonName(final String ribbonName) {
        this.ribbonName = ribbonName;
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RibbonDefinition)) {
            return false;
        }
        final RibbonDefinition other = (RibbonDefinition)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serviceName = this.getServiceName();
        final Object other$serviceName = other.getServiceName();
        Label_0065: {
            if (this$serviceName == null) {
                if (other$serviceName == null) {
                    break Label_0065;
                }
            }
            else if (this$serviceName.equals(other$serviceName)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$ribbonClass = this.getRibbonClass();
        final Object other$ribbonClass = other.getRibbonClass();
        Label_0102: {
            if (this$ribbonClass == null) {
                if (other$ribbonClass == null) {
                    break Label_0102;
                }
            }
            else if (this$ribbonClass.equals(other$ribbonClass)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$ribbonName = this.getRibbonName();
        final Object other$ribbonName = other.getRibbonName();
        if (this$ribbonName == null) {
            if (other$ribbonName == null) {
                return true;
            }
        }
        else if (this$ribbonName.equals(other$ribbonName)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RibbonDefinition;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serviceName = this.getServiceName();
        result = result * 59 + (($serviceName == null) ? 43 : $serviceName.hashCode());
        final Object $ribbonClass = this.getRibbonClass();
        result = result * 59 + (($ribbonClass == null) ? 43 : $ribbonClass.hashCode());
        final Object $ribbonName = this.getRibbonName();
        result = result * 59 + (($ribbonName == null) ? 43 : $ribbonName.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RibbonDefinition(serviceName=" + this.getServiceName() + ", ribbonClass=" + this.getRibbonClass() + ", ribbonName=" + this.getRibbonName() + ")";
    }
}
