package com.open.cloud.workflow.framework.entity;

/**
 * ACT_GE_BYTEARRAY
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class ActGeBytearray implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -7022519986046285935L;

    /** id */
    private String id;

    /** rev */
    private Integer rev;

    /** name */
    private String name;

    /** deploymentId */
    private String deploymentId;

    /** bytes */
    private byte[] bytes;

    /** generated */
    private String generated;

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
     * 获取rev
     * 
     * @return rev
     */
    public Integer getRev() {
        return this.rev;
    }

    /**
     * 设置rev
     * 
     * @param rev
     */
    public void setRev(Integer rev) {
        this.rev = rev;
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
     * 获取bytes
     * 
     * @return bytes
     */
    public byte[] getBytes() {
        return this.bytes;
    }

    /**
     * 设置bytes
     * 
     * @param bytes
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * 获取generated
     * 
     * @return generated
     */
    public String getGenerated() {
        return this.generated;
    }

    /**
     * 设置generated
     * 
     * @param generated
     */
    public void setGenerated(String generated) {
        this.generated = generated;
    }
}