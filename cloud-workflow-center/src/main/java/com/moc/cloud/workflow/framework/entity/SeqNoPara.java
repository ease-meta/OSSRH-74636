package com.moc.cloud.workflow.framework.entity;

/**
 * SEQ_NO_PARA
 * 
 * @author leijian
 * @version 1.0.0 2019-01-14
 */
public class SeqNoPara implements java.io.Serializable {
    /** 版本号 */
    private static final long serialVersionUID = -3631517179068549849L;

    /** seqType */
    private String seqType;

    /** minSeq */
    private String minSeq;

    /** maxSeq */
    private String maxSeq;

    /** currSeq */
    private String currSeq;

    /** countSeq */
    private String countSeq;

    /**
     * 获取seqType
     * 
     * @return seqType
     */
    public String getSeqType() {
        return this.seqType;
    }

    /**
     * 设置seqType
     * 
     * @param seqType
     */
    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }

    /**
     * 获取minSeq
     * 
     * @return minSeq
     */
    public String getMinSeq() {
        return this.minSeq;
    }

    /**
     * 设置minSeq
     * 
     * @param minSeq
     */
    public void setMinSeq(String minSeq) {
        this.minSeq = minSeq;
    }

    /**
     * 获取maxSeq
     * 
     * @return maxSeq
     */
    public String getMaxSeq() {
        return this.maxSeq;
    }

    /**
     * 设置maxSeq
     * 
     * @param maxSeq
     */
    public void setMaxSeq(String maxSeq) {
        this.maxSeq = maxSeq;
    }

    /**
     * 获取currSeq
     * 
     * @return currSeq
     */
    public String getCurrSeq() {
        return this.currSeq;
    }

    /**
     * 设置currSeq
     * 
     * @param currSeq
     */
    public void setCurrSeq(String currSeq) {
        this.currSeq = currSeq;
    }

    /**
     * 获取countSeq
     * 
     * @return countSeq
     */
    public String getCountSeq() {
        return this.countSeq;
    }

    /**
     * 设置countSeq
     * 
     * @param countSeq
     */
    public void setCountSeq(String countSeq) {
        this.countSeq = countSeq;
    }
}