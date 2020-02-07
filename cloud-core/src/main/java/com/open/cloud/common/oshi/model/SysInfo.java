package com.open.cloud.common.oshi.model;

import lombok.Data;

/**
 * @author Leijian
 * @date   2020/2/7
 */
@Data
public class SysInfo {

    /**
     * 服务器名称
     */
    private String computerName;

    /**
     * 服务器Ip
     */
    private String computerIp;

    /**
     * 项目路径
     */
    private String userDir;

    /**
     * 操作系统
     */
    private String osName;

    /**
     * 系统架构
     */
    private String osArch;

}
