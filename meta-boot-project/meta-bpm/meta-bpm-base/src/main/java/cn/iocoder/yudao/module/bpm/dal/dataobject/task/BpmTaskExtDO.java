/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.iocoder.yudao.module.bpm.dal.dataobject.task;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.github.meta.ease.mybatis.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/**
 * Bpm 流程任务的拓展表
 * 主要解决 Activiti Task 和 HistoricTaskInstance 不支持拓展字段，所以新建拓展表
 *
 * @author 芋道源码
 */
@TableName(value = "bpm_task_ext", autoResultMap = true)
@KeySequence("bpm_task_ext_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BpmTaskExtDO extends BaseDO {

    /**
     * 编号，自增
     */
    @TableId
    private Long id;
    /**
     * 任务的审批人
     * <p>
     * 冗余 Task 的 assignee 属性
     */
    private Long assigneeUserId;
    /**
     * 任务的名字
     * <p>
     * 冗余 Task 的 name 属性，为了筛选
     */
    private String name;
    /**
     * 任务的编号
     * <p>
     * 关联 Task 的 id 属性
     */
    private String taskId;
    //    /**
    //     * 任务的标识
    //     *
    //     * 关联 {@link Task#getTaskDefinitionKey()}
    //     */
    //    private String definitionKey;
    /**
     * 任务的结果
     * <p>
     * 枚举 {@link cn.iocoder.yudao.module.bpm.enums.task.BpmProcessInstanceResultEnum}
     */
    private Integer result;
    /**
     * 审批建议
     */
    private String reason;
    /**
     * 任务的结束时间
     * <p>
     * 冗余 HistoricTaskInstance 的 endTime  属性
     */
    private Date endTime;

    /**
     * 流程实例的编号
     * <p>
     * 关联 ProcessInstance 的 id 属性
     */
    private String processInstanceId;
    /**
     * 流程定义的编号
     * <p>
     * 关联 ProcessDefinition 的 id 属性
     */
    private String processDefinitionId;

}
