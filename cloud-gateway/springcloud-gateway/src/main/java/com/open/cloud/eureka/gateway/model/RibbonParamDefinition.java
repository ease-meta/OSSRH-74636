package com.open.cloud.eureka.gateway.model;

import com.fasterxml.jackson.annotation.*;

public class RibbonParamDefinition
{
    @JsonProperty("ribbon_name")
    String ribbonName;
    @JsonProperty("ribbon_class")
    String ribbonClass;
    
    public String getRibbonName() {
        return this.ribbonName;
    }
    
    public String getRibbonClass() {
        return this.ribbonClass;
    }
    
    public RibbonParamDefinition setRibbonName(final String ribbonName) {
        this.ribbonName = ribbonName;
        return this;
    }
    
    public RibbonParamDefinition setRibbonClass(final String ribbonClass) {
        this.ribbonClass = ribbonClass;
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RibbonParamDefinition)) {
            return false;
        }
        final RibbonParamDefinition other = (RibbonParamDefinition)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$ribbonName = this.getRibbonName();
        final Object other$ribbonName = other.getRibbonName();
        Label_0065: {
            if (this$ribbonName == null) {
                if (other$ribbonName == null) {
                    break Label_0065;
                }
            }
            else if (this$ribbonName.equals(other$ribbonName)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$ribbonClass = this.getRibbonClass();
        final Object other$ribbonClass = other.getRibbonClass();
        if (this$ribbonClass == null) {
            if (other$ribbonClass == null) {
                return true;
            }
        }
        else if (this$ribbonClass.equals(other$ribbonClass)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RibbonParamDefinition;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $ribbonName = this.getRibbonName();
        result = result * 59 + (($ribbonName == null) ? 43 : $ribbonName.hashCode());
        final Object $ribbonClass = this.getRibbonClass();
        result = result * 59 + (($ribbonClass == null) ? 43 : $ribbonClass.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RibbonParamDefinition(ribbonName=" + this.getRibbonName() + ", ribbonClass=" + this.getRibbonClass() + ")";
    }
}
