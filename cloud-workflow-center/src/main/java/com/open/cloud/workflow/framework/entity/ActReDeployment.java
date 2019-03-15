package com.open.cloud.workflow.framework.entity;
import java.sql.Timestamp;

/**
 * ACT_RE_DEPLOYMENT
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActReDeployment implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -3400564552099700992L;

    /** id */
    private String id;

    /** name */
    private String name;

    /** category */
    private String category;

    /** key */
    private String key;

    /** tenantId */
    private String tenantId;

    /** deployTime */
    private Timestamp deployTime;

    /** engineVersion */
    private String engineVersion;

    /**
     * 获取id
     * 
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取name
     * 
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取category
     * 
     * @return category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * 设置category
     * 
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取key
     * 
     * @return key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * 设置key
     * 
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取tenantId
     * 
     * @return tenantId
     */
    public String getTenantId() {
        return this.tenantId;
    }

    /**
     * 设置tenantId
     * 
     * @param tenantId
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 获取deployTime
     * 
     * @return deployTime
     */
    public Timestamp getDeployTime() {
        return this.deployTime;
    }

    /**
     * 设置deployTime
     * 
     * @param deployTime
     */
    public void setDeployTime(Timestamp deployTime) {
        this.deployTime = deployTime;
    }

    /**
     * 获取engineVersion
     * 
     * @return engineVersion
     */
    public String getEngineVersion() {
        return this.engineVersion;
    }

    /**
     * 设置engineVersion
     * 
     * @param engineVersion
     */
    public void setEngineVersion(String engineVersion) {
        this.engineVersion = engineVersion;
    }
}