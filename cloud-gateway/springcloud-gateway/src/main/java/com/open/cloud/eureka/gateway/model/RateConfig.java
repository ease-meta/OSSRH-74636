package com.open.cloud.eureka.gateway.model;

public class RateConfig
{
    private String keyResolver;
    private String burstCapacity;
    private String replenishRate;
    private String key;
    private String value;
    private String scope;
    
    public String getKeyResolver() {
        return this.keyResolver;
    }
    
    public String getBurstCapacity() {
        return this.burstCapacity;
    }
    
    public String getReplenishRate() {
        return this.replenishRate;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getScope() {
        return this.scope;
    }
    
    public void setKeyResolver(final String keyResolver) {
        this.keyResolver = keyResolver;
    }
    
    public void setBurstCapacity(final String burstCapacity) {
        this.burstCapacity = burstCapacity;
    }
    
    public void setReplenishRate(final String replenishRate) {
        this.replenishRate = replenishRate;
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public void setValue(final String value) {
        this.value = value;
    }
    
    public void setScope(final String scope) {
        this.scope = scope;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RateConfig)) {
            return false;
        }
        final RateConfig other = (RateConfig)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$keyResolver = this.getKeyResolver();
        final Object other$keyResolver = other.getKeyResolver();
        Label_0065: {
            if (this$keyResolver == null) {
                if (other$keyResolver == null) {
                    break Label_0065;
                }
            }
            else if (this$keyResolver.equals(other$keyResolver)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$burstCapacity = this.getBurstCapacity();
        final Object other$burstCapacity = other.getBurstCapacity();
        Label_0102: {
            if (this$burstCapacity == null) {
                if (other$burstCapacity == null) {
                    break Label_0102;
                }
            }
            else if (this$burstCapacity.equals(other$burstCapacity)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$replenishRate = this.getReplenishRate();
        final Object other$replenishRate = other.getReplenishRate();
        Label_0139: {
            if (this$replenishRate == null) {
                if (other$replenishRate == null) {
                    break Label_0139;
                }
            }
            else if (this$replenishRate.equals(other$replenishRate)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        Label_0176: {
            if (this$key == null) {
                if (other$key == null) {
                    break Label_0176;
                }
            }
            else if (this$key.equals(other$key)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        Label_0213: {
            if (this$value == null) {
                if (other$value == null) {
                    break Label_0213;
                }
            }
            else if (this$value.equals(other$value)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$scope = this.getScope();
        final Object other$scope = other.getScope();
        if (this$scope == null) {
            if (other$scope == null) {
                return true;
            }
        }
        else if (this$scope.equals(other$scope)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof RateConfig;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $keyResolver = this.getKeyResolver();
        result = result * 59 + (($keyResolver == null) ? 43 : $keyResolver.hashCode());
        final Object $burstCapacity = this.getBurstCapacity();
        result = result * 59 + (($burstCapacity == null) ? 43 : $burstCapacity.hashCode());
        final Object $replenishRate = this.getReplenishRate();
        result = result * 59 + (($replenishRate == null) ? 43 : $replenishRate.hashCode());
        final Object $key = this.getKey();
        result = result * 59 + (($key == null) ? 43 : $key.hashCode());
        final Object $value = this.getValue();
        result = result * 59 + (($value == null) ? 43 : $value.hashCode());
        final Object $scope = this.getScope();
        result = result * 59 + (($scope == null) ? 43 : $scope.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "RateConfig(keyResolver=" + this.getKeyResolver() + ", burstCapacity=" + this.getBurstCapacity() + ", replenishRate=" + this.getReplenishRate() + ", key=" + this.getKey() + ", value=" + this.getValue() + ", scope=" + this.getScope() + ")";
    }
}
