package com.open.cloud.mq.rocketmq.config;

import com.open.cloud.mq.rocketmq.RocketMQConsumerProvider;
import com.open.cloud.mq.rocketmq.RocketMQProducerProvider;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Leijian
 * @date 2020/2/3
 */
@Configuration
public class RocketMQConfiguration {

	@Bean
	public RocketMQProducerProvider rocketMQProducerProvider(DefaultMQProducer defaultMQProducer) {
		return new RocketMQProducerProvider(defaultMQProducer);
	}

	@Bean
	public RocketMQConsumerProvider rocketMQConsumerProvider(RocketMQProperties rocketMQProperties) {
		return new RocketMQConsumerProvider(rocketMQProperties);
	}

}
