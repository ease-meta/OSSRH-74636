package com.open.cloud.test.web.mybatis.demo.entity;

import com.open.cloud.mybatis.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author leijian
 * @since 2021-10-11
 */
@TableName("act_app_databasechangeloglock")
public class ActAppDatabasechangeloglock implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Boolean locked;

    private LocalDateTime lockgranted;

    private String lockedby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getLockgranted() {
        return lockgranted;
    }

    public void setLockgranted(LocalDateTime lockgranted) {
        this.lockgranted = lockgranted;
    }

    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
    }

    @Override
    public String toString() {
        return "ActAppDatabasechangeloglock{" +
                "id=" + id +
                ", locked=" + locked +
                ", lockgranted=" + lockgranted +
                ", lockedby=" + lockedby +
                "}";
    }
}
