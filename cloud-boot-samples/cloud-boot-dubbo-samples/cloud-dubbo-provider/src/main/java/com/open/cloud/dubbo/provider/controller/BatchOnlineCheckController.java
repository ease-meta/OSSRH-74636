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
package com.open.cloud.dubbo.provider.controller;

import java.util.Arrays;

import com.open.cloud.dubbo.provider.R;
import com.open.cloud.dubbo.provider.entity.BatchOnlineCheckEntity;
import com.open.cloud.dubbo.provider.service.BatchOnlineCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件登记表(UPRGHT)
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-04-19 20:14:13
 */
@RestController
@RequestMapping("generator/batchonlinecheck")
public class BatchOnlineCheckController {
    @Autowired
    private BatchOnlineCheckService batchOnlineCheckService;

    private TransactionDefinition   definition = new DefaultTransactionDefinition(
                                                   TransactionDefinition.PROPAGATION_REQUIRES_NEW);

    @Autowired
    PlatformTransactionManager      platformTransactionManager;

    /**
     * 信息
     */
    @RequestMapping("/info/{jobId}")
    public R info(@PathVariable("jobId") String jobId) {
        BatchOnlineCheckEntity batchOnlineCheck = batchOnlineCheckService.getByJobId(jobId);
        return R.ok().put("batchOnlineCheck", batchOnlineCheck);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BatchOnlineCheckEntity batchOnlineCheck) {
        batchOnlineCheckService.save(batchOnlineCheck);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BatchOnlineCheckEntity batchOnlineCheck) {
        batchOnlineCheckService.updateById(batchOnlineCheck);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] jobIds) {
        batchOnlineCheckService.removeByIds(Arrays.asList(jobIds));
        return R.ok();
    }

}
