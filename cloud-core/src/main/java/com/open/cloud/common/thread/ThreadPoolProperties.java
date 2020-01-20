package com.open.cloud.common.thread;

import com.open.cloud.common.utils.PropertyUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class ThreadPoolProperties {
    public static String Prefix="open.cloud.threadpool.";
    public static int getThreadPoolMaxSize() {
        return PropertyUtils.getPropertyCache(Prefix+"max",500);
    }
    public static int getThreadPoolMinSize() {
        return PropertyUtils.getPropertyCache(Prefix+"min",0);
    }
}