package com.open.cloud.mq.base;

import com.open.cloud.common.system.ProcessExitEvent;
import com.open.cloud.common.utils.LogUtils;
import lombok.Data;

import java.lang.reflect.Method;

@Data
public abstract class AbstractMQ implements AutoCloseable {

	private Object object;

	public AbstractMQ() {
		ProcessExitEvent.register(() -> {
			try {
				this.close();
			} catch (Exception exp) {
				LogUtils.warn(AbstractMQ.class, MQProperties.Project, "应用退出时释放mq资源异常", exp);
			}
		}, 1, false);
	}

	protected void innerClose(Object object) {
		try {
			if (object != null) {
				LogUtils.debug(AbstractMQ.class, MQProperties.Project, "MQ准备释放内部资源...");
				String[] methods = {"close", "shutdown"};
				for (String name : methods) {
					Method method = object.getClass().getDeclaredMethod(name);
					if (method != null) {
						method.invoke(object);
					}
				}
			}
		} catch (Exception exp) {
			LogUtils.warn(AbstractMQ.class, MQProperties.Project, "MQ内部资源释放异常", exp);
		}
	}
}
