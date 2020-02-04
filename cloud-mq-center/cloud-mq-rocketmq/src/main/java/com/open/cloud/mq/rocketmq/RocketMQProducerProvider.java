package com.open.cloud.mq.rocketmq;

import com.open.cloud.common.serialize.JsonSerializer;
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
			if (msg instanceof String) {
				msgJson = (String) msg;
			} else {
				msgJson = new JsonSerializer().serialize(msg);
			}
			Message message = new Message(topic, StringUtils.isEmpty(tag) ? "" : tag, msgJson.getBytes(RemotingHelper.DEFAULT_CHARSET));
			object.send(message);
			return producer;
		} catch (Exception exp) {
			LogUtils.error(RocketMQProducerProvider.class, Project, "生产者消息发送失败", exp);
			throw new MqException(exp);
		}
	}
}
