package com.open.cloud.framework.utils;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

/**
 * @author leijian
 * @date 2019年1月13日
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
public class Log {

    /**
     * 参数
     */
    private Map<String, String[]> parameterMap;
    /**
     * requestBody
     */
    private Object requestBody;
    /**
     * 请求路径
     */
    private String url;
    /**
     * 请求mapping
     */
    private String mapping;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 日志需要打印的json字符串
     */
    private Object result;
    /**
     * 接口运行时间 单位:ms
     */
    private String runTime;
    /**
     * IP地址
     */
    private String ip;
    /**
     * UID
     */
    private String uid;

}
