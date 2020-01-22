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
package com.open.cloud.common.system;

import com.open.cloud.common.base.Callable;
import com.open.cloud.common.config.CoreProperties;
import com.open.cloud.common.utils.LogUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.val;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Leijian
 */
public class ProcessExitEvent {
	private static ArrayList<ExitCallback> callBackList = new ArrayList<>();
	private static Object lock = new Object();

	/**
	 * @param action0
	 * @param order   越大越晚 必须大于0
	 */
	public static void register(Callable.Action0 action0, int order, Boolean asynch) {
		synchronized (lock) {
			callBackList.add(new ExitCallback(action0, Math.abs(order), asynch));
		}
	}

	static {
		//JVM 停止或重启时
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				synchronized (lock) {
					callBackList.sort(Comparator.comparingInt(c -> c.order));
					for (val a : callBackList) {
						//Callable.Action0 action0 = a.action0;
						Callable.Action0 method = () -> {
							try {
								a.action0.invoke();
							} catch (Exception e2) {
								LogUtils.error(ProcessExitEvent.class, CoreProperties.Project, "进程关闭事件回调处理出错", e2);
							}
						};
						if (a.asynch) {
							new Thread(() -> {
								method.invoke();
							}).start();
						} else {
							method.invoke();
						}
					}
				}
				LogUtils.info(ProcessExitEvent.class, CoreProperties.Project, "应用已正常退出！");
			} catch (Exception e) {
				LogUtils.error(ProcessExitEvent.class, CoreProperties.Project, "进程关闭事件回调处理出错", e);
			}
		}));
	}

	@Data
	@AllArgsConstructor
	private static class ExitCallback {
		Callable.Action0 action0;
		/**
		 * 顺序
		 */
		Integer order;
		/**
		 * 异步支持
		 */
		Boolean asynch = false;
	}
}
