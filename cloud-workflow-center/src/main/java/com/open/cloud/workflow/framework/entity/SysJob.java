package com.open.cloud.workflow.framework.entity;
import java.util.Date;

/**
 * SYS_JOB
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class SysJob implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -8859118368139742793L;

    /** id */
    private Integer id;

    /** 描述任务 */
    private String jobName;

    /** 任务表达式 */
    private String cron;

    /** 状态:0未启动false/1启动true */
    private Boolean status;

    /** 任务执行方法 */
    private String clazzPath;

    /** 其他描述 */
    private String jobDesc;

    /** createBy */
    private String createBy;

    /** createDate */
    private Date createDate;

    /** updateBy */
    private String updateBy;

    /** updateDate */
    private Date updateDate;

    /** ip */
    private String ip;

    /**
     * 获取id
     * 
     * @return id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     * 
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取描述任务
     * 
     * @return 描述任务
     */
    public String getJobName() {
        return this.jobName;
    }

    /**
     * 设置描述任务
     * 
     * @param jobName
     *          描述任务
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * 获取任务表达式
     * 
     * @return 任务表达式
     */
    public String getCron() {
        return this.cron;
    }

    /**
     * 设置任务表达式
     * 
     * @param cron
     *          任务表达式
     */
    public void setCron(String cron) {
        this.cron = cron;
    }

    /**
     * 获取状态:0未启动false/1启动true
     * 
     * @return 状态
     */
    public Boolean getStatus() {
        return this.status;
    }

    /**
     * 设置状态:0未启动false/1启动true
     * 
     * @param status
     *          状态
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取任务执行方法
     * 
     * @return 任务执行方法
     */
    public String getClazzPath() {
        return this.clazzPath;
    }

    /**
     * 设置任务执行方法
     * 
     * @param clazzPath
     *          任务执行方法
     */
    public void setClazzPath(String clazzPath) {
        this.clazzPath = clazzPath;
    }

    /**
     * 获取其他描述
     * 
     * @return 其他描述
     */
    public String getJobDesc() {
        return this.jobDesc;
    }

    /**
     * 设置其他描述
     * 
     * @param jobDesc
     *          其他描述
     */
    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    /**
     * 获取createBy
     * 
     * @return createBy
     */
    public String getCreateBy() {
        return this.createBy;
    }

    /**
     * 设置createBy
     * 
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取createDate
     * 
     * @return createDate
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置createDate
     * 
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取updateBy
     * 
     * @return updateBy
     */
    public String getUpdateBy() {
        return this.updateBy;
    }

    /**
     * 设置updateBy
     * 
     * @param updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取updateDate
     * 
     * @return updateDate
     */
    public Date getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 设置updateDate
     * 
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取ip
     * 
     * @return ip
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置ip
     * 
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}