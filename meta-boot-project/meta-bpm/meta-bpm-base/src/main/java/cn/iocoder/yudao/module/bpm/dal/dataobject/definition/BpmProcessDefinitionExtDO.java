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
package cn.iocoder.yudao.module.bpm.dal.dataobject.definition;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * Bpm 流程定义的拓展表
 * 主要解决 Activiti {@link ProcessDefinition} 不支持拓展字段，所以新建拓展表
 *
 * @author 芋道源码
 */
@TableName(value = "bpm_process_definition_ext", autoResultMap = true)
@KeySequence("bpm_process_definition_ext_seq")
// 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BpmProcessDefinitionExtDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 流程定义的编号
     * <p>
     * 关联 ProcessDefinition 的 id 属性
     */
    private String processDefinitionId;
    /**
     * 流程模型的编号
     * <p>
     * 关联 Model 的 id 属性
     */
    private String modelId;
    /**
     * 描述
     */
    private String description;

    /**
     * 表单类型
     * <p>
     * 关联 {@link cn.iocoder.yudao.module.bpm.enums.definition.BpmModelFormTypeEnum}
     */
    private Integer formType;
    /**
     * 动态表单编号
     * 在表单类型为 {@link cn.iocoder.yudao.module.bpm.enums.definition.BpmModelFormTypeEnum#NORMAL} 时
     * <p>
     * 关联 {@link cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmFormDO#getId()}
     */
    private Long formId;
    /**
     * 表单的配置
     * 在表单类型为 {@link cn.iocoder.yudao.module.bpm.enums.definition.BpmModelFormTypeEnum#NORMAL} 时
     * <p>
     * 冗余 {@link cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmFormDO#getConf()}
     */
    private String formConf;
    /**
     * 表单项的数组
     * 在表单类型为 {@link cn.iocoder.yudao.module.bpm.enums.definition.BpmModelFormTypeEnum#NORMAL} 时
     * <p>
     * 冗余 {@link cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmFormDO#getFields()} ()}
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> formFields;
    /**
     * 自定义表单的提交路径，使用 Vue 的路由地址
     * 在表单类型为 {@link cn.iocoder.yudao.module.bpm.enums.definition.BpmModelFormTypeEnum#CUSTOM} 时
     */
    private String formCustomCreatePath;
    /**
     * 自定义表单的查看路径，使用 Vue 的路由地址
     * 在表单类型为 {@link cn.iocoder.yudao.module.bpm.enums.definition.BpmModelFormTypeEnum#CUSTOM} 时
     */
    private String formCustomViewPath;

}
