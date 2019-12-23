package com.open.cloud.framework;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class ContextRefreshListener implements ApplicationListener<ApplicationEvent> {

    private final ApplicationEventPublisher publisher;

    private HeartbeatMonitor monitor = new HeartbeatMonitor();

    public ContextRefreshListener(ApplicationEventPublisher publisher) {
        Assert.notNull(publisher, "publisher may not be null");
        this.publisher = publisher;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextRefreshedEvent) {
            reset();
        }
    }

    private void resetIfNeeded(Object value) {
        if (this.monitor.update(value)) {
            reset();
        }
    }

    private void reset() {
        this.publisher.publishEvent(new RefreshContextEvent(this));
    }
}