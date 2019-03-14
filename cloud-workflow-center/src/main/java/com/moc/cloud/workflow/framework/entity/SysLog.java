package com.moc.cloud.workflow.framework.entity;

import java.time.LocalDateTime;

/**
 * 系统日志(SYS_LOG)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class SysLog implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = 6143770827031549608L;

    /** 系统日志表主键 */
    private String id;

    /** 执行者 */
    private String userName;

    /** IP地址 */
    private String ip;

    /** 请求类型 */
    private String type;

    /** url地址 */
    private String url;

    /** 请求内容 */
    private String text;

    /** 参数 */
    private String param;

    /** 请求时间 */
    private LocalDateTime createTime;

    /**
     * 获取系统日志表主键
     * 
     * @return 系统日志表主键
     */
    public String getId() {
        return this.id;
    }

    /**
     * 设置系统日志表主键
     * 
     * @param id
     *          系统日志表主键
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取执行者
     * 
     * @return 执行者
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置执行者
     * 
     * @param userName
     *          执行者
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取IP地址
     * 
     * @return IP地址
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置IP地址
     * 
     * @param ip
     *          IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取请求类型
     * 
     * @return 请求类型
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置请求类型
     * 
     * @param type
     *          请求类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取url地址
     * 
     * @return url地址
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置url地址
     * 
     * @param url
     *          url地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取请求内容
     * 
     * @return 请求内容
     */
    public String getText() {
        return this.text;
    }

    /**
     * 设置请求内容
     * 
     * @param text
     *          请求内容
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 获取参数
     * 
     * @return 参数
     */
    public String getParam() {
        return this.param;
    }

    /**
     * 设置参数
     * 
     * @param param
     *          参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 获取请求时间
     * 
     * @return 请求时间
     */
    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置请求时间
     * 
     * @param createTime
     *          请求时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}