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
package com.open.cloud.sofa.provider.service.impl;

import com.open.cloud.sofa.provider.dao.BatchOnlineCheckDao;
import com.open.cloud.sofa.provider.entity.BatchOnlineCheckEntity;
import com.open.cloud.sofa.provider.service.BatchOnlineCheckService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service("batchOnlineCheckService")
public class BatchOnlineCheckServiceImpl extends
                                        ServiceImpl<BatchOnlineCheckDao, BatchOnlineCheckEntity>
                                                                                                implements
                                                                                                BatchOnlineCheckService {

    @Cacheable(value = "business#6*10")
    public BatchOnlineCheckEntity getByJobId(String id) {
        return list().get(0);
    }
}