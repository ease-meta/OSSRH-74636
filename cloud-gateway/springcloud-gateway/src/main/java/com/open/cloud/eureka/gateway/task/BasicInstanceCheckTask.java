package com.open.cloud.eureka.gateway.task;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.TimerTask;

@Component
@ConfigurationProperties(prefix = "timer")
public abstract class BasicInstanceCheckTask extends TimerTask
{
    @Override
    public abstract void run();
    
    public abstract void init();
    
    abstract void updateLocalInstances();
}
