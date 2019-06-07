package com.open.cloud.test.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/7 12:35
 **/
@Entity
@Table(name = "mq_consumer_msg", schema = "workflow", catalog = "")
public class MqConsumerMsgEntity {
    private String mqMessageId;
    private String msgId;
    private String bornTime;
    private String bornHost;
    private String storeTime;
    private String storeHost;
    private String topic;
    private String tag;
    private Integer queueId;
    private String receiveTime;
    private Integer status;
    private String updateTime;
    private String remark;

    @Id
    @Column(name = "mq_message_id", nullable = false, length = 36)
    public String getMqMessageId() {
        return mqMessageId;
    }

    public void setMqMessageId(String mqMessageId) {
        this.mqMessageId = mqMessageId;
    }

    @Basic
    @Column(name = "msg_id", nullable = true, length = 36)
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Basic
    @Column(name = "born_time", nullable = true, length = 20)
    public String getBornTime() {
        return bornTime;
    }

    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }

    @Basic
    @Column(name = "born_host", nullable = true, length = 36)
    public String getBornHost() {
        return bornHost;
    }

    public void setBornHost(String bornHost) {
        this.bornHost = bornHost;
    }

    @Basic
    @Column(name = "store_time", nullable = true, length = 20)
    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    @Basic
    @Column(name = "store_host", nullable = true, length = 36)
    public String getStoreHost() {
        return storeHost;
    }

    public void setStoreHost(String storeHost) {
        this.storeHost = storeHost;
    }

    @Basic
    @Column(name = "topic", nullable = true, length = 36)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "tag", nullable = true, length = 10)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "queue_id", nullable = true)
    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    @Basic
    @Column(name = "receive_time", nullable = true, length = 20)
    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "update_time", nullable = true, length = 20)
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 2000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mqMessageId, msgId, bornTime, bornHost, storeTime, storeHost, topic, tag, queueId, receiveTime, status, updateTime, remark);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MqConsumerMsgEntity that = (MqConsumerMsgEntity) o;
        return Objects.equals(mqMessageId, that.mqMessageId) &&
                Objects.equals(msgId, that.msgId) &&
                Objects.equals(bornTime, that.bornTime) &&
                Objects.equals(bornHost, that.bornHost) &&
                Objects.equals(storeTime, that.storeTime) &&
                Objects.equals(storeHost, that.storeHost) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(queueId, that.queueId) &&
                Objects.equals(receiveTime, that.receiveTime) &&
                Objects.equals(status, that.status) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(remark, that.remark);
    }
}
