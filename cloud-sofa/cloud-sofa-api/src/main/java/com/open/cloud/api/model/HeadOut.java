package com.open.cloud.api.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leijian
 * @version 1.0
 * @date 2019/6/14 19:10
 **/
@Data
public class HeadOut extends BaseData {

    private String runDate;
    private String downFilePath;
    private String retStatus;

    /**
     * 返回结果集
     */
    private List<Result> ret;


    public HeadOut(String retStatus, Result result) {
        this.retStatus = retStatus;
        this.ret = new ArrayList<>();
        this.ret.add(result);
    }
}
