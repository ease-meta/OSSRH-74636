package com.open.cloud.workflow.framework.entity;
import java.util.Date;

/**
 * 流程历史定义，表结构同act_re_procdef(ACT_HI_REPROCDEF)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActHiReprocdef implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 2529539789455091789L;

    /** 主键,流程实例的外键 */
    private String id;

    /** 版本号 */
    private Integer rev;

    /** 流程定义类型 */
    private String category;

    /** 流程中文描述 */
    private String name;

    /** 流程文件定义的ID */
    private String key;

    /** 流程定义的版本号 */
    private Integer version;

    /** deploymentId */
    private String deploymentId;

    /** 流程定义文件 */
    private String resourceName;

    /** dgrmResourceName */
    private String dgrmResourceName;

    /** 流程定义描述信息 */
    private String description;

    /** hasStartFormKey */
    private Integer hasStartFormKey;

    /** hasGraphicalNotation */
    private Integer hasGraphicalNotation;

    /** 流程状态0禁用1启用 */
    private String suspensionState;

    /** tenantId */
    private String tenantId;

    /** engineVersion */
    private String engineVersion;

    /** 流程修改时间 */
    private Date resourceTime;

    /**
     * 获取主键,流程实例的外键
     * 
     * @return 主键
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置主键,流程实例的外键
     * 
     * @param id
     *          主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取版本号
     * 
     * @return 版本号
     */
    public Integer getRev() {
        return this.rev;
    }

    /**
     * 设置版本号
     * 
     * @param rev
     *          版本号
     */
    public void setRev(Integer rev) {
        this.rev = rev;
    }

    /**
     * 获取流程定义类型
     * 
     * @return 流程定义类型
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * 设置流程定义类型
     * 
     * @param category
     *          流程定义类型
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取流程中文描述
     * 
     * @return 流程中文描述
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置流程中文描述
     * 
     * @param name
     *          流程中文描述
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取流程文件定义的ID
     * 
     * @return 流程文件定义的ID
     */
    public String getKey() {
        return this.key;
    }

    /**
     * 设置流程文件定义的ID
     * 
     * @param key
     *          流程文件定义的ID
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取流程定义的版本号
     * 
     * @return 流程定义的版本号
     */
    public Integer getVersion() {
        return this.version;
    }

    /**
     * 设置流程定义的版本号
     * 
     * @param version
     *          流程定义的版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取deploymentId
     * 
     * @return deploymentId
     */
    public String getDeploymentId() {
        return this.deploymentId;
    }

    /**
     * 设置deploymentId
     * 
     * @param deploymentId
     */
    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    /**
     * 获取流程定义文件
     * 
     * @return 流程定义文件
     */
    public String getResourceName() {
        return this.resourceName;
    }

    /**
     * 设置流程定义文件
     * 
     * @param resourceName
     *          流程定义文件
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * 获取dgrmResourceName
     * 
     * @return dgrmResourceName
     */
    public String getDgrmResourceName() {
        return this.dgrmResourceName;
    }

    /**
     * 设置dgrmResourceName
     * 
     * @param dgrmResourceName
     */
    public void setDgrmResourceName(String dgrmResourceName) {
        this.dgrmResourceName = dgrmResourceName;
    }

    /**
     * 获取流程定义描述信息
     * 
     * @return 流程定义描述信息
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置流程定义描述信息
     * 
     * @param description
     *          流程定义描述信息
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取hasStartFormKey
     * 
     * @return hasStartFormKey
     */
    public Integer getHasStartFormKey() {
        return this.hasStartFormKey;
    }

    /**
     * 设置hasStartFormKey
     * 
     * @param hasStartFormKey
     */
    public void setHasStartFormKey(Integer hasStartFormKey) {
        this.hasStartFormKey = hasStartFormKey;
    }

    /**
     * 获取hasGraphicalNotation
     * 
     * @return hasGraphicalNotation
     */
    public Integer getHasGraphicalNotation() {
        return this.hasGraphicalNotation;
    }

    /**
     * 设置hasGraphicalNotation
     * 
     * @param hasGraphicalNotation
     */
    public void setHasGraphicalNotation(Integer hasGraphicalNotation) {
        this.hasGraphicalNotation = hasGraphicalNotation;
    }

    /**
     * 获取流程状态0禁用1启用
     * 
     * @return 流程状态0禁用1启用
     */
    public String getSuspensionState() {
        return this.suspensionState;
    }

    /**
     * 设置流程状态0禁用1启用
     * 
     * @param suspensionState
     *          流程状态0禁用1启用
     */
    public void setSuspensionState(String suspensionState) {
        this.suspensionState = suspensionState;
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

    /**
     * 获取流程修改时间
     * 
     * @return 流程修改时间
     */
    public Date getResourceTime() {
        return this.resourceTime;
    }

    /**
     * 设置流程修改时间
     * 
     * @param resourceTime
     *          流程修改时间
     */
    public void setResourceTime(Date resourceTime) {
        this.resourceTime = resourceTime;
    }
}