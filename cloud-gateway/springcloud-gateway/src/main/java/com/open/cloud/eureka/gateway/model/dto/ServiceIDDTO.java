package com.open.cloud.eureka.gateway.model.dto;

public class ServiceIDDTO
{
    private String serviceID;
    private String instanceID;
    
    public String getServiceID() {
        return this.serviceID;
    }
    
    public String getInstanceID() {
        return this.instanceID;
    }
    
    public ServiceIDDTO setServiceID(final String serviceID) {
        this.serviceID = serviceID;
        return this;
    }
    
    public ServiceIDDTO setInstanceID(final String instanceID) {
        this.instanceID = instanceID;
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ServiceIDDTO)) {
            return false;
        }
        final ServiceIDDTO other = (ServiceIDDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$serviceID = this.getServiceID();
        final Object other$serviceID = other.getServiceID();
        Label_0065: {
            if (this$serviceID == null) {
                if (other$serviceID == null) {
                    break Label_0065;
                }
            }
            else if (this$serviceID.equals(other$serviceID)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$instanceID = this.getInstanceID();
        final Object other$instanceID = other.getInstanceID();
        if (this$instanceID == null) {
            if (other$instanceID == null) {
                return true;
            }
        }
        else if (this$instanceID.equals(other$instanceID)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ServiceIDDTO;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $serviceID = this.getServiceID();
        result = result * 59 + (($serviceID == null) ? 43 : $serviceID.hashCode());
        final Object $instanceID = this.getInstanceID();
        result = result * 59 + (($instanceID == null) ? 43 : $instanceID.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ServiceIDDTO(serviceID=" + this.getServiceID() + ", instanceID=" + this.getInstanceID() + ")";
    }
}
