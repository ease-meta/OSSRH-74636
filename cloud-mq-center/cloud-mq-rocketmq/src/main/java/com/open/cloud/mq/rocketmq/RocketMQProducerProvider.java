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
package com.open.cloud.mq.rocketmq;

import com.open.cloud.common.serializer.JsonSerializer;
import com.open.cloud.common.utils.LogUtils;
import com.open.cloud.mq.base.AbstractProducer;
import com.open.cloud.mq.base.AbstractProducerProvider;
import com.open.cloud.mq.base.exception.MqException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import static com.open.cloud.mq.rocketmq.RocketMQProperties.Project;

/**
 * @author Leijian
 * @date 2020/2/3
 */
public class RocketMQProducerProvider extends AbstractProducerProvider {

	protected volatile DefaultMQProducer object;

	protected AbstractProducer producer;

	protected Object _lock = new Object();

	public RocketMQProducerProvider(DefaultMQProducer object) {
		this.object = object;
		this.producer = new AbstractProducer();
		//producer.setObject();
	}

	public void setObject(DefaultMQProducer defaultMQProducer) {
		this.object = defaultMQProducer;
	}

	public DefaultMQProducer getObject() {
		return object;
	}

	@Override
	public <T> AbstractProducer sendMessage(String topic, String tag, T msg) {
		try {
			String msgJson = null;
			byte[] body;
			if (msg instanceof String) {
				body =((String) msg).getBytes(RemotingHelper.DEFAULT_CHARSET);
			} else {
				body = new JsonSerializer().serialize(msg);
			}
			Message message = new Message(topic, StringUtils.isEmpty(tag) ? "" : tag, body);
			object.send(message);
			return producer;
		} catch (Exception exp) {
			LogUtils.error(RocketMQProducerProvider.class, Project, "生产者消息发送失败", exp);
			throw new MqException(exp);
		}
	}
}
