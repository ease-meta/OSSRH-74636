package com.open.cloud.mq.base;

import com.open.cloud.common.utils.LogUtils;

public class AbstractProducer extends AbstractMQ {
	@Override
	public void close() {
		try {
			LogUtils.debug(AbstractProducer.class, MQProperties.Project, "MQ生产者准备释放资源...");
			Object obj = getObject();
			/*if (obj instanceof DefaultMQProducer) {
				DefaultMQProducer mqProducer = ((DefaultMQProducer) obj);
				mqProducer.shutdown();
				LogUtils.info(AbstractProducer.class, MQProperties.Project, "rocketmq 生产者释放资源完毕");
				obj = null;
			}*/
			innerClose(obj);
		} catch (Exception exp) {
			LogUtils.warn(AbstractProducer.class, MQProperties.Project, "MQ生产者资源释放异常", exp);
		}
	}
}
