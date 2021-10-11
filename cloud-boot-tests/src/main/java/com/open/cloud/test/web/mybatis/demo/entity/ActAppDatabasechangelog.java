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
@TableName("act_app_databasechangelog")
public class ActAppDatabasechangelog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String author;

    private String filename;

    private LocalDateTime dateexecuted;

    private Integer orderexecuted;

    private String exectype;

    private String md5sum;

    private String description;

    private String comments;

    private String tag;

    private String liquibase;

    private String contexts;

    private String labels;

    private String deploymentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getDateexecuted() {
        return dateexecuted;
    }

    public void setDateexecuted(LocalDateTime dateexecuted) {
        this.dateexecuted = dateexecuted;
    }

    public Integer getOrderexecuted() {
        return orderexecuted;
    }

    public void setOrderexecuted(Integer orderexecuted) {
        this.orderexecuted = orderexecuted;
    }

    public String getExectype() {
        return exectype;
    }

    public void setExectype(String exectype) {
        this.exectype = exectype;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLiquibase() {
        return liquibase;
    }

    public void setLiquibase(String liquibase) {
        this.liquibase = liquibase;
    }

    public String getContexts() {
        return contexts;
    }

    public void setContexts(String contexts) {
        this.contexts = contexts;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }

    @Override
    public String toString() {
        return "ActAppDatabasechangelog{" +
                "id=" + id +
                ", author=" + author +
                ", filename=" + filename +
                ", dateexecuted=" + dateexecuted +
                ", orderexecuted=" + orderexecuted +
                ", exectype=" + exectype +
                ", md5sum=" + md5sum +
                ", description=" + description +
                ", comments=" + comments +
                ", tag=" + tag +
                ", liquibase=" + liquibase +
                ", contexts=" + contexts +
                ", labels=" + labels +
                ", deploymentId=" + deploymentId +
                "}";
    }
}
