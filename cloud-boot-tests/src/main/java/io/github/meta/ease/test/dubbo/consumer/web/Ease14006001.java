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
package io.github.meta.ease.test.dubbo.consumer.web;

import io.github.meta.ease.test.api.IEase14006001;
import io.github.meta.ease.test.api.module.Ease14006001In;
import io.github.meta.ease.test.api.module.Ease14006001Out;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/1 18:10
 */
@RestController
@Service
public class Ease14006001 {

    @DubboReference
    private IEase14006001 iEase14006001;

    @PostMapping("/ease/14006001/")
    public Ease14006001Out runService(@RequestBody Ease14006001In in) {
        return iEase14006001.runService(in);
    }
}
