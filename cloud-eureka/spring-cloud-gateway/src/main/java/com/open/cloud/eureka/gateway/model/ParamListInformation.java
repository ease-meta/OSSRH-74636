package com.open.cloud.eureka.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ParamListInformation
{
    private String name;
    private String comment;
    private List<ParamInformation> args;
    @JsonIgnore
    private Integer weight;
    
    public String getName() {
        return this.name;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public List<ParamInformation> getArgs() {
        return this.args;
    }
    
    public Integer getWeight() {
        return this.weight;
    }
    
    public ParamListInformation setName(final String name) {
        this.name = name;
        return this;
    }
    
    public ParamListInformation setComment(final String comment) {
        this.comment = comment;
        return this;
    }
    
    public ParamListInformation setArgs(final List<ParamInformation> args) {
        this.args = args;
        return this;
    }
    
    public ParamListInformation setWeight(final Integer weight) {
        this.weight = weight;
        return this;
    }
    
    @Override
    public String toString() {
        return "ParamListInformation(name=" + this.getName() + ", comment=" + this.getComment() + ", args=" + this.getArgs() + ", weight=" + this.getWeight() + ")";
    }
}
