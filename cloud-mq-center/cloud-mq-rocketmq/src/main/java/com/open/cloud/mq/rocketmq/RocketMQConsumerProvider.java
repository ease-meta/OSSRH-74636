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

import com.open.cloud.common.serialize.JsonSerializer;
import com.open.cloud.common.utils.LogUtils;
import com.open.cloud.mq.base.AbstractConsumer;
import com.open.cloud.mq.base.AbstractConsumerProvider;
import com.open.cloud.mq.base.Message;
import com.open.cloud.mq.base.SubscribeRunable;
import com.open.cloud.mq.base.exception.MqException;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @author Leijian
 * @date 2020/2/3
 */
public class RocketMQConsumerProvider extends AbstractConsumerProvider {

	private org.apache.rocketmq.spring.autoconfigure.RocketMQProperties rocketMQProperties;

	public RocketMQConsumerProvider(org.apache.rocketmq.spring.autoconfigure.RocketMQProperties rocketMQProperties) {
		this.rocketMQProperties = rocketMQProperties;
	}

	public void setRocketMQProperties(org.apache.rocketmq.spring.autoconfigure.RocketMQProperties rocketMQProperties) {
		this.rocketMQProperties = rocketMQProperties;
	}

	public org.apache.rocketmq.spring.autoconfigure.RocketMQProperties getRocketMQProperties() {
		return rocketMQProperties;
	}

	@Override
	public <T> AbstractConsumer subscribe(String consumergroup, String topic, String[] filterTags, SubscribeRunable<T> runnable, Class<T> type) {
		return subscribe(consumergroup, MessageModel.CLUSTERING, topic, filterTags, runnable, type);
	}

	public <T> AbstractConsumer subscribe(String consumergroup, MessageModel mode, String topic, String[] filterTags, SubscribeRunable<T> runnable, Class<T> type) {

		DefaultMQPushConsumer consumer = null;
		try {
			consumer = new DefaultMQPushConsumer(consumergroup);
			// Specify name server addresses.
			consumer.setNamesrvAddr(rocketMQProperties.getNameServer());
			String filtertag = null;
			// Subscribe one more more topics to consume.
			if (filterTags == null || filterTags.length == 0) {
				filtertag = "*";
			} else {
				filtertag = StringUtils.join(filterTags, "||");
			}
			consumer.subscribe(topic, filtertag);
			// Register callback to execute on arrival of messages fetched from brokers.
			consumer.registerMessageListener(new MessageListenerConcurrently() {
				@Override
				public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
					MessageExt messageExt = msgs.get(0);
					try {
						String id = messageExt.getMsgId();
						byte[] body = messageExt.getBody();
						String tags = messageExt.getTags();
						String topic = messageExt.getTopic();
						String keys = messageExt.getKeys();
						String msg = new String(body, RemotingHelper.DEFAULT_CHARSET);
						if (type == String.class) {
							runnable.run(new Message<T>(id, topic, tags, (T) msg));
						} else {
							runnable.run(new Message<T>(id, topic, tags, new JsonSerializer().deserialize(msg, type)));
						}
						return null;
					} catch (Exception e) {
						LogUtils.error(RocketMQConsumerProvider.class, RocketMQProperties.Project, String.format("rocketmq 消费者%s,消费异常", consumergroup), e);
						//处理出现异常，获取重试次数.达到某个次数的时候可以记录日志，做补偿处理
						int reconsumeTimes = messageExt.getReconsumeTimes();
						/*if (reconsumeTimes < rocketMQProperties.getReconsumeTimes()) {
							return ConsumeConcurrentlyStatus.RECONSUME_LATER;
						}*/
					}

					return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
				}
			});
			consumer.start();
			AbstractConsumer abstractConsumer = new AbstractConsumer();
			abstractConsumer.setObject(consumer);
			LogUtils.info(RocketMQConsumerProvider.class, RocketMQProperties.Project, String.format("rocketmq 消费者%s,队列%s 启动成功", consumergroup, topic));
			return abstractConsumer;
		} catch (Exception exp) {
			LogUtils.error(RocketMQConsumerProvider.class, RocketMQProperties.Project, String.format("rocketmq 消费者%s,队列%s 启动失败", consumergroup, topic), exp);
			if (consumer != null) {
				consumer.shutdown();
				consumer = null;
			}
			throw new MqException(exp);
		}
	}

}
