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
package com.open.cloud;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

@WithStateMachine
@Data
@Slf4j
public class BizBean {

    /**
     * @see States
     */
    private String status = States.DRAFT.name();

    @OnTransition(target = "PUBLISH_TODO")
    public void online() {
        log.info("操作上线，待发布. target status:{}", States.PUBLISH_TODO.name());
        setStatus(States.PUBLISH_TODO.name());
    }

    @OnTransition(target = "PUBLISH_DONE")
    public void publish() {
        log.info("操作发布,发布完成. target status:{}", States.PUBLISH_DONE.name());
        setStatus(States.PUBLISH_DONE.name());
    }

    @OnTransition(target = "DRAFT")
    public void rollback() {
        log.info("操作回滚,回到草稿状态. target status:{}", States.DRAFT.name());
        setStatus(States.DRAFT.name());
    }

}
