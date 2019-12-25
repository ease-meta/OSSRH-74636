package com.open.cloud.eureka.gateway.util;

import org.springframework.context.event.*;
import org.springframework.stereotype.*;
import org.springframework.context.*;

@Service
public class ServiceInitListener implements ApplicationListener<ContextStartedEvent>
{
    public void onApplicationEvent(final ContextStartedEvent contextStartedEvent) {
        ServiceHandler.initTask();
    }
}
