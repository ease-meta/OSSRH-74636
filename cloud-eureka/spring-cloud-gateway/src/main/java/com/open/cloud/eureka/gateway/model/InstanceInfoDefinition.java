package com.open.cloud.eureka.gateway.model;

import java.net.*;
import org.springframework.cloud.client.*;
import java.util.*;
import org.slf4j.*;

public class InstanceInfoDefinition implements ServiceInstance
{
    private static final Logger log;
    private String appName;
    private String hostName;
    private int port;
    private boolean secure;
    private Map<String, String> metadata;
    private String instanceId;
    private InstanceStatus status;
    
    public String getServiceId() {
        return this.appName;
    }
    
    public String getHost() {
        return this.hostName;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public boolean isSecure() {
        return this.secure;
    }
    
    public URI getUri() {
        return DefaultServiceInstance.getUri((ServiceInstance)this);
    }
    
    public Map<String, String> getMetadata() {
        return this.metadata;
    }
    
    public String getInstanceId() {
        return this.instanceId;
    }
    
    public InstanceStatus getStatus() {
        return this.status;
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public String getHostName() {
        return this.hostName;
    }
    
    public void setAppName(final String appName) {
        this.appName = appName;
    }
    
    public void setHostName(final String hostName) {
        this.hostName = hostName;
    }
    
    public void setPort(final int port) {
        this.port = port;
    }
    
    public void setSecure(final boolean secure) {
        this.secure = secure;
    }
    
    public void setMetadata(final Map<String, String> metadata) {
        this.metadata = metadata;
    }
    
    public void setInstanceId(final String instanceId) {
        this.instanceId = instanceId;
    }
    
    public void setStatus(final InstanceStatus status) {
        this.status = status;
    }
    
    public InstanceInfoDefinition(final InstanceInfoDefinition info) {
        this.metadata = new HashMap<String, String>();
        this.appName = info.getAppName();
        this.hostName = info.getHostName();
        this.instanceId = info.getInstanceId();
        this.metadata = info.getMetadata();
        this.port = info.getPort();
        this.secure = info.isSecure();
        this.status = info.getStatus();
    }
    
    public InstanceInfoDefinition() {
        this.metadata = new HashMap<String, String>();
    }
    
    static {
        log = LoggerFactory.getLogger((Class)InstanceInfoDefinition.class);
    }
    
    public enum InstanceStatus
    {
        UP, 
        DOWN, 
        STARTING, 
        OUT_OF_SERVICE, 
        UNKNOWN;
        
        public static InstanceStatus toEnum(final String s) {
            if (s != null) {
                try {
                    return valueOf(s.toUpperCase());
                }
                catch (IllegalArgumentException e) {
                    InstanceInfoDefinition.log.debug("illegal argument supplied to InstanceStatus.valueOf: {}, defaulting to {}", (Object)s, (Object)InstanceStatus.UNKNOWN);
                }
            }
            return InstanceStatus.UNKNOWN;
        }
    }
}
