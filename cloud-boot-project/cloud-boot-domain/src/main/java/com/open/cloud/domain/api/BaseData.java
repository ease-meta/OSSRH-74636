package com.open.cloud.domain.api;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseData implements Serializable {


    private String flowId;


    private Integer id;


    private String processStatus;


    private LocalDateTime updateTime;


    private String updateUser;


    private String createUser;


    private LocalDateTime createTime;


    private String tenantId;


    private String appId;

    private String processId;


    private String operationType;


}
