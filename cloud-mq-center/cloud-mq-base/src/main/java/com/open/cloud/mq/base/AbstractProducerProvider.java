package com.open.cloud.mq.base;

public class AbstractProducerProvider extends AbstractProvider {
	
	public <T> AbstractProducer sendMessage(String topic, String tag, T msg) {
		return null;
	}
}