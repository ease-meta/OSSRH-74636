package com.open.cloud.eureka.gateway.common;


import java.io.Serializable;

public class PaginatedResult implements Serializable
{
    private static final long serialVersionUID = 6191745064790884707L;
    private int currentPage;
    private int totalPage;
    private int count;
    private Object data;
    
    public int getCurrentPage() {
        return this.currentPage;
    }
    
    public int getTotalPage() {
        return this.totalPage;
    }
    
    public int getCount() {
        return this.count;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public PaginatedResult setCurrentPage(final int currentPage) {
        this.currentPage = currentPage;
        return this;
    }
    
    public PaginatedResult setTotalPage(final int totalPage) {
        this.totalPage = totalPage;
        return this;
    }
    
    public PaginatedResult setCount(final int count) {
        this.count = count;
        return this;
    }
    
    public PaginatedResult setData(final Object data) {
        this.data = data;
        return this;
    }
    
    @Override
    public String toString() {
        return "PaginatedResult(currentPage=" + this.getCurrentPage() + ", totalPage=" + this.getTotalPage() + ", count=" + this.getCount() + ", data=" + this.getData() + ")";
    }
}
