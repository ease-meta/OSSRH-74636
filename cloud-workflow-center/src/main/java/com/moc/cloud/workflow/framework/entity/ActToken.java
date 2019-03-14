package com.moc.cloud.workflow.framework.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @Classname: DcActToken
 * @Description:
 * @Author: leijian
 * @Date: 2019/1/30
 * @Version: 1.0
 */
@Entity
@EqualsAndHashCode(callSuper = false)
public class ActToken implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 127593201694027397L;

	@Id
	private String uuid;
    private String token;
    private LocalDateTime time;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(final LocalDateTime time) {
        this.time = time;
    }


}
