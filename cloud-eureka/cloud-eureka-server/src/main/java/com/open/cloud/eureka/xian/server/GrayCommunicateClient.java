package com.open.cloud.eureka.xian.server;

import com.netflix.appinfo.InstanceInfo;

public interface GrayCommunicateClient {


    /**
     * 将实例信息发送到灰度服务器
     *
     * @param instanceInfo 实例信息
     */
    void noticeInstanceInfo(InstanceInfo instanceInfo);

}