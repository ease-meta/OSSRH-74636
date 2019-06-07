package com.open.cloud.test.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SeqNoPara implements java.io.Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = -3631517179068549849L;

    /**
     * seqType
     */
    private String seqType;

    /**
     * minSeq
     */
    private String minSeq;

    /**
     * maxSeq
     */
    private String maxSeq;

    /**
     * currSeq
     */
    private String currSeq;

    /**
     * countSeq
     */
    private String countSeq;

}