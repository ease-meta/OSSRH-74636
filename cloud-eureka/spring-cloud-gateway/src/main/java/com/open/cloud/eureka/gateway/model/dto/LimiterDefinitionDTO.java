package com.open.cloud.eureka.gateway.model.dto;


import com.open.cloud.eureka.gateway.model.ParamListInformation;

public class LimiterDefinitionDTO
{
    private String id;
    private ParamListInformation predicate;
    private ParamListInformation filter;
    
    public String getId() {
        return this.id;
    }
    
    public ParamListInformation getPredicate() {
        return this.predicate;
    }
    
    public ParamListInformation getFilter() {
        return this.filter;
    }
    
    public void setId(final String id) {
        this.id = id;
    }
    
    public void setPredicate(final ParamListInformation predicate) {
        this.predicate = predicate;
    }
    
    public void setFilter(final ParamListInformation filter) {
        this.filter = filter;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LimiterDefinitionDTO)) {
            return false;
        }
        final LimiterDefinitionDTO other = (LimiterDefinitionDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        Label_0065: {
            if (this$id == null) {
                if (other$id == null) {
                    break Label_0065;
                }
            }
            else if (this$id.equals(other$id)) {
                break Label_0065;
            }
            return false;
        }
        final Object this$predicate = this.getPredicate();
        final Object other$predicate = other.getPredicate();
        Label_0102: {
            if (this$predicate == null) {
                if (other$predicate == null) {
                    break Label_0102;
                }
            }
            else if (this$predicate.equals(other$predicate)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$filter = this.getFilter();
        final Object other$filter = other.getFilter();
        if (this$filter == null) {
            if (other$filter == null) {
                return true;
            }
        }
        else if (this$filter.equals(other$filter)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof LimiterDefinitionDTO;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * 59 + (($id == null) ? 43 : $id.hashCode());
        final Object $predicate = this.getPredicate();
        result = result * 59 + (($predicate == null) ? 43 : $predicate.hashCode());
        final Object $filter = this.getFilter();
        result = result * 59 + (($filter == null) ? 43 : $filter.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "LimiterDefinitionDTO(id=" + this.getId() + ", predicate=" + this.getPredicate() + ", filter=" + this.getFilter() + ")";
    }
}
