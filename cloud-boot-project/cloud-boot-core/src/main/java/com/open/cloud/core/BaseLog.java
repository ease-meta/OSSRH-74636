package com.open.cloud.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseLog {

    protected Logger log = null;

    public BaseLog() {
        log = LoggerFactory.getLogger(this.getClass().getName());
    }
}