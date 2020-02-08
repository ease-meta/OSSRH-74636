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
