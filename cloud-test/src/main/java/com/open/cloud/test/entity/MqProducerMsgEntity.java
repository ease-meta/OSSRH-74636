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
@Table(name = "mq_producer_msg", schema = "workflow", catalog = "")
public class MqProducerMsgEntity {
    private String mqMsgId;
    private String flowId;
    private String brokerName;
    private String offsetMsgId;
    private String msgId;
    private String message;
    private String createTime;
    private String lastUpdate;
    private Integer status;
    private String messageType;
    private Integer seqNo;
    private Integer queueId;

    @Id
    @Column(name = "mq_msg_id", nullable = false, length = 36)
    public String getMqMsgId() {
        return mqMsgId;
    }

    public void setMqMsgId(String mqMsgId) {
        this.mqMsgId = mqMsgId;
    }

    @Basic
    @Column(name = "flow_id", nullable = true, length = 36)
    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    @Basic
    @Column(name = "broker_name", nullable = true, length = 36)
    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    @Basic
    @Column(name = "offset_msg_id", nullable = true, length = 36)
    public String getOffsetMsgId() {
        return offsetMsgId;
    }

    public void setOffsetMsgId(String offsetMsgId) {
        this.offsetMsgId = offsetMsgId;
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
    @Column(name = "message", nullable = true, length = 256)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "create_time", nullable = true, length = 20)
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_update", nullable = true, length = 20)
    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
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
    @Column(name = "message_type", nullable = true, length = 10)
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Basic
    @Column(name = "seq_no", nullable = true)
    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    @Basic
    @Column(name = "queue_id", nullable = true)
    public Integer getQueueId() {
        return queueId;
    }

    public void setQueueId(Integer queueId) {
        this.queueId = queueId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mqMsgId, flowId, brokerName, offsetMsgId, msgId, message, createTime, lastUpdate, status, messageType, seqNo, queueId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MqProducerMsgEntity that = (MqProducerMsgEntity) o;
        return Objects.equals(mqMsgId, that.mqMsgId) &&
                Objects.equals(flowId, that.flowId) &&
                Objects.equals(brokerName, that.brokerName) &&
                Objects.equals(offsetMsgId, that.offsetMsgId) &&
                Objects.equals(msgId, that.msgId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(lastUpdate, that.lastUpdate) &&
                Objects.equals(status, that.status) &&
                Objects.equals(messageType, that.messageType) &&
                Objects.equals(seqNo, that.seqNo) &&
                Objects.equals(queueId, that.queueId);
    }
}
