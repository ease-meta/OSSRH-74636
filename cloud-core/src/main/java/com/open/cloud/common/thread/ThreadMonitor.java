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
package com.open.cloud.common.thread;

import com.open.cloud.common.base.Collector;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Leijian
 */
public class ThreadMonitor {
    private ThreadPoolExecutor threadPoolExecutor;
    private String             name;

    public ThreadMonitor(String name, ThreadPoolExecutor threadPoolExecutor) {
		this.threadPoolExecutor = threadPoolExecutor;
		this.name = name;
		Collector.Default.call(name + ".active.count").set(() -> {
			return threadPoolExecutor == null ? 0 : threadPoolExecutor.getActiveCount();
		});
		Collector.Default.call(name + ".core.poolSize").set(() -> {
			return threadPoolExecutor == null ? 0 : threadPoolExecutor.getCorePoolSize();
		});
		Collector.Default.call(name + ".poolSize.largest").set(() -> {
			return threadPoolExecutor == null ? 0 : threadPoolExecutor.getLargestPoolSize();
		});
		Collector.Default.call(name + ".poolSize.max").set(() -> {
			return threadPoolExecutor == null ? 0 : threadPoolExecutor.getMaximumPoolSize();
		});
		Collector.Default.call(name + ".poolSize.count").set(() -> {
			return threadPoolExecutor == null ? 0 : threadPoolExecutor.getPoolSize();
		});
		Collector.Default.call(name + ".queue.size").set(() -> {
			return threadPoolExecutor == null ? 0 : threadPoolExecutor.getQueue().size();
		});
		Collector.Default.call(name + ".task.count").set(() -> {
			return threadPoolExecutor == null ? 0 : threadPoolExecutor.getTaskCount();
		});
		Collector.Default.call(name + ".task.completed").set(() -> {
			return threadPoolExecutor == null ? 0 : threadPoolExecutor.getCompletedTaskCount();
		});
	}

    public Collector.Hook hook() {
        return Collector.Default.hook(name + ".hook");
    }
}