package com.open.cloud.eureka.gateway.model;

import com.alibaba.fastjson.annotation.*;

public class SystemRequest
{
    @JSONField(name = "sysHead")
    private SystemHeader sysHead;
    
    public SystemRequest setSysHead(final SystemHeader sysHead) {
        this.sysHead = sysHead;
        return this;
    }
    
    public SystemHeader getSysHead() {
        return this.sysHead;
    }
}
