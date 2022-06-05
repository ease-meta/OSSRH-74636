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
package cn.iocoder.yudao.module.bpm.service.definition.dto;

import lombok.Data;

/**
 * BPM 流程 MetaInfo Response DTO
 * 主要用于 { Model#setMetaInfo(String)} 的存储
 *
 * @author 芋道源码
 */
@Data
public class BpmModelMetaInfoRespDTO {

    /**
     * 流程描述
     */
    private String description;

    /**
     * 表单类型
     */
    private Integer formType;

    /**
     * 表单编号
     * 在表单类型为 {@link cn.iocoder.yudao.module.bpm.enums.definition.BpmModelFormTypeEnum#NORMAL} 时
     */
    private Long formId;

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
