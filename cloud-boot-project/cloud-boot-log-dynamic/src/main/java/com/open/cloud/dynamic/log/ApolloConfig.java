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
package com.open.cloud.dynamic.log;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * @author leijian
 * @date 2020/11/30 15:22
 */
@Slf4j
public class ApolloConfig {

    /**
     * Invoked when there is any config change for the namespace.
     *
     * @param changeEvent the event for this change
     */
    @ApolloConfigChangeListener(value = {"application", "logging"})
    private void onChangeLogging(ConfigChangeEvent changeEvent) {
        if (!"logging".equalsIgnoreCase(changeEvent.getNamespace())) {
            return;
        }
        log.info("change log value by apollo start ");
        Set<String> changedKeys = changeEvent.changedKeys();
        this.handChangeEvent(changedKeys, changeEvent);
        log.info("change log value by apollo end ");
    }

    private synchronized void handChangeEvent(Set<String> chanagedKeys,
                                              ConfigChangeEvent changeEvent) {
        Set<String> keys = changeEvent.changedKeys();
        ConfigChange change;
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        HashMap<String, String> logMappings = new HashMap<>();
        for (String key : keys) {
            change = changeEvent.getChange(key);
            log.info(String.format("Change - key: %s, oldValue: %s, newValue: %s, changeType: %s",
                    change.getPropertyName(), change.getOldValue(), change.getNewValue(),
                    change.getChangeType()));

            String targetValues = change.getNewValue();

            if ("root".equalsIgnoreCase(key)) {
                LogUpdateFactory.updateRootlevel(targetValues);
            }

            if (targetValues == null || targetValues.isEmpty() || targetValues.startsWith("logging")) {
                continue;
            }

            logMappings.put(key, targetValues);

            log.info("Auto update apollo changed value successfully, new value: {}, {}", key,
                    targetValues);
        }
        LogUpdateFactory.updateLogMappings(logMappings);
    }
}
