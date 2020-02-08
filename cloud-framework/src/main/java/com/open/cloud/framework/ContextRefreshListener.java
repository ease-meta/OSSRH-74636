/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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