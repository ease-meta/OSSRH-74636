package com.moc.cloud.workflow.framework.entity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 用户信息表(SYS_USER)
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class SysUser implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -1864661988616962289L;

    /** userId */
    @NotNull(message="用户ID不能为空")
    private String userId;

    /** username */
    private String username;

    /** password */
    private String password;

    /** age */
    private Integer age;

    /** email */
    private String email;

    /** photo */
    private String photo;

    /** realName */
    private String realName;

    /** createBy */
    private String createBy;

    /** updateBy */
    private String updateBy;

    /** createDate */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createDate;

    /** updateDate */
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateDate;

    /** 0不可用*/
    private Boolean delFlag;

    /**
     * 获取userId
     * 
     * @return userId
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * 设置userId
     * 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取username
     * 
     * @return username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * 设置username
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取password
     * 
     * @return password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置password
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取age
     * 
     * @return age
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * 设置age
     * 
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取email
     * 
     * @return email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置email
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取photo
     * 
     * @return photo
     */
    public String getPhoto() {
        return this.photo;
    }

    /**
     * 设置photo
     * 
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取realName
     * 
     * @return realName
     */
    public String getRealName() {
        return this.realName;
    }

    /**
     * 设置realName
     * 
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
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
     * 获取createDate
     * 
     * @return createDate
     */
    public LocalDateTime getCreateDate() {
        return this.createDate;
    }

    /**
     * 设置createDate
     * 
     * @param createDate
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取updateDate
     * 
     * @return updateDate
     */
    public LocalDateTime getUpdateDate() {
        return this.updateDate;
    }

    /**
     * 设置updateDate
     * 
     * @param updateDate
     */
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取0可用1封禁
     * 
     * @return 0可用1封禁
     */
    public Boolean getDelFlag() {
        return this.delFlag;
    }

    /**
     * 设置0可用1封禁
     * 
     * @param delFlag
     *          0可用1封禁
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getCredentialsSalt() {
        return username + password;
    }
}