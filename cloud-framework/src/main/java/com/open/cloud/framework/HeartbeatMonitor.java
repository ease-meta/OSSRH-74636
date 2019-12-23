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