package com.open.cloud.mq.base;

public class AbstractConsumerProvider extends AbstractProvider {
	public <T> AbstractConsumer subscribe(String consumergroup, String topic, String[] filterTags, SubscribeRunable<T> runnable, Class<T> type) {
		return null;
	}
}