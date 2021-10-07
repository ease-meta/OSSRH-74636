package com.open.cloud.core.flow.api;

/**
 * 交易参考号
 *
 * @author Tim
 * @date 2015 /7/8
 */
public interface BusinessTraceNo {

    /**
     * 生成交易参考号
     *
     * @return 交易参考号
     */
    String generator();

}