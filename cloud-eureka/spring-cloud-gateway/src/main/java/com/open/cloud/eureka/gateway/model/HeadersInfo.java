package com.open.cloud.eureka.gateway.model;

public class HeadersInfo
{
    private String name;
    private String desc;
    
    public String getName() {
        return this.name;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setDesc(final String desc) {
        this.desc = desc;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HeadersInfo)) {
            return false;
        }
        final HeadersInfo other = (HeadersInfo)o;
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
        final Object this$desc = this.getDesc();
        final Object other$desc = other.getDesc();
        if (this$desc == null) {
            if (other$desc == null) {
                return true;
            }
        }
        else if (this$desc.equals(other$desc)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof HeadersInfo;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $desc = this.getDesc();
        result = result * 59 + (($desc == null) ? 43 : $desc.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "HeadersInfo(name=" + this.getName() + ", desc=" + this.getDesc() + ")";
    }
}
