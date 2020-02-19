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
package com.open.cloud.mq.base;

import com.open.cloud.common.utils.LogUtils;

public class AbstractConsumer extends AbstractMQ {
    @Override
    public void close() {
        try {
            LogUtils.debug(AbstractConsumer.class, MQProperties.Project, "MQ消费者准备释放资源...");
            Object obj = getObject();
            /*if (obj != null) {
                if (obj instanceof DefaultMQPushConsumer) {
                    DefaultMQPushConsumer consumer = ((DefaultMQPushConsumer) obj);
                    LogUtils.info(AbstractConsumer.class,MQProperties.Project,String.format("rocketmq 消费者:%s 释放资源完毕", consumer.getConsumerGroup()));
                    consumer.shutdown();
                    obj = null;
                }
            }*/
            innerClose(obj);
        } catch (Exception exp) {
            LogUtils.warn(AbstractConsumer.class, MQProperties.Project, "MQ消费者资源释放异常", exp);
        }

    }
}
