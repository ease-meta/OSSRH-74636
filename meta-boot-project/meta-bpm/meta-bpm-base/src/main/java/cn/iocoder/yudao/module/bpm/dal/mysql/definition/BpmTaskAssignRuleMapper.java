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
package cn.iocoder.yudao.module.bpm.dal.mysql.definition;

import cn.iocoder.yudao.module.bpm.dal.dataobject.definition.BpmTaskAssignRuleDO;
import io.github.meta.ease.mybatis.mybatis.core.mapper.BaseMapperX;
import io.github.meta.ease.mybatis.mybatis.core.query.LambdaQueryWrapperX;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.Nullable;

import java.util.List;

@Mapper
public interface BpmTaskAssignRuleMapper extends BaseMapperX<BpmTaskAssignRuleDO> {

    default List<BpmTaskAssignRuleDO> selectListByProcessDefinitionId(String processDefinitionId,
                                                                      @Nullable String taskDefinitionKey) {
        return selectList(new LambdaQueryWrapperX<BpmTaskAssignRuleDO>()
                .eq(BpmTaskAssignRuleDO::getProcessDefinitionId, processDefinitionId)
                .eqIfPresent(BpmTaskAssignRuleDO::getTaskDefinitionKey, taskDefinitionKey));
    }

    default List<BpmTaskAssignRuleDO> selectListByModelId(String modelId) {
        return selectList(new LambdaQueryWrapperX<BpmTaskAssignRuleDO>()
                .eq(BpmTaskAssignRuleDO::getModelId, modelId)
                .eq(BpmTaskAssignRuleDO::getProcessDefinitionId, BpmTaskAssignRuleDO.PROCESS_DEFINITION_ID_NULL));
    }

    default BpmTaskAssignRuleDO selectListByModelIdAndTaskDefinitionKey(String modelId,
                                                                        String taskDefinitionKey) {
        return selectOne(new LambdaQueryWrapperX<BpmTaskAssignRuleDO>()
                .eq(BpmTaskAssignRuleDO::getModelId, modelId)
                .eq(BpmTaskAssignRuleDO::getProcessDefinitionId, BpmTaskAssignRuleDO.PROCESS_DEFINITION_ID_NULL)
                .eq(BpmTaskAssignRuleDO::getTaskDefinitionKey, taskDefinitionKey));
    }

}
