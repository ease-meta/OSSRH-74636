package com.open.cloud.eureka.gateway.model;

public class ParamInformation
{
    private String name;
    private String placeholder;
    private String comment;
    private String type;
    private String eg;
    private String value;
    
    public String getName() {
        return this.name;
    }
    
    public String getPlaceholder() {
        return this.placeholder;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getEg() {
        return this.eg;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public ParamInformation setName(final String name) {
        this.name = name;
        return this;
    }
    
    public ParamInformation setPlaceholder(final String placeholder) {
        this.placeholder = placeholder;
        return this;
    }
    
    public ParamInformation setComment(final String comment) {
        this.comment = comment;
        return this;
    }
    
    public ParamInformation setType(final String type) {
        this.type = type;
        return this;
    }
    
    public ParamInformation setEg(final String eg) {
        this.eg = eg;
        return this;
    }
    
    public ParamInformation setValue(final String value) {
        this.value = value;
        return this;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ParamInformation)) {
            return false;
        }
        final ParamInformation other = (ParamInformation)o;
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
        final Object this$placeholder = this.getPlaceholder();
        final Object other$placeholder = other.getPlaceholder();
        Label_0102: {
            if (this$placeholder == null) {
                if (other$placeholder == null) {
                    break Label_0102;
                }
            }
            else if (this$placeholder.equals(other$placeholder)) {
                break Label_0102;
            }
            return false;
        }
        final Object this$comment = this.getComment();
        final Object other$comment = other.getComment();
        Label_0139: {
            if (this$comment == null) {
                if (other$comment == null) {
                    break Label_0139;
                }
            }
            else if (this$comment.equals(other$comment)) {
                break Label_0139;
            }
            return false;
        }
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        Label_0176: {
            if (this$type == null) {
                if (other$type == null) {
                    break Label_0176;
                }
            }
            else if (this$type.equals(other$type)) {
                break Label_0176;
            }
            return false;
        }
        final Object this$eg = this.getEg();
        final Object other$eg = other.getEg();
        Label_0213: {
            if (this$eg == null) {
                if (other$eg == null) {
                    break Label_0213;
                }
            }
            else if (this$eg.equals(other$eg)) {
                break Label_0213;
            }
            return false;
        }
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null) {
            if (other$value == null) {
                return true;
            }
        }
        else if (this$value.equals(other$value)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ParamInformation;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * 59 + (($name == null) ? 43 : $name.hashCode());
        final Object $placeholder = this.getPlaceholder();
        result = result * 59 + (($placeholder == null) ? 43 : $placeholder.hashCode());
        final Object $comment = this.getComment();
        result = result * 59 + (($comment == null) ? 43 : $comment.hashCode());
        final Object $type = this.getType();
        result = result * 59 + (($type == null) ? 43 : $type.hashCode());
        final Object $eg = this.getEg();
        result = result * 59 + (($eg == null) ? 43 : $eg.hashCode());
        final Object $value = this.getValue();
        result = result * 59 + (($value == null) ? 43 : $value.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "ParamInformation(name=" + this.getName() + ", placeholder=" + this.getPlaceholder() + ", comment=" + this.getComment() + ", type=" + this.getType() + ", eg=" + this.getEg() + ", value=" + this.getValue() + ")";
    }
}
