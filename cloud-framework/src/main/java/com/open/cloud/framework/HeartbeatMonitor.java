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

import java.util.concurrent.atomic.AtomicReference;

public class HeartbeatMonitor {

    private AtomicReference<Object> latestHeartbeat = new AtomicReference<>();

    /**
     * @param value The latest heartbeat.
     * @return True if the state changed.
     */
    public boolean update(Object value) {
        Object last = this.latestHeartbeat.get();
        if (value != null && !value.equals(last)) {
            return this.latestHeartbeat.compareAndSet(last, value);
        }
        return false;
    }

}