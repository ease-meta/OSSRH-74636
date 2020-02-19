package com.open.cloud.leaf.core.common;

/**
 * @author Leijian
 * @date 2020/2/17
 */
public class CheckVO {

    private long timestamp;

    private int  workID;

    public CheckVO(long timestamp, int workID) {
        this.timestamp = timestamp;
        this.workID = workID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }
}
