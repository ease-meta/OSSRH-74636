package com.open.cloud.common.thread;

import com.open.cloud.common.base.Collector;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Leijian
 */
public class ThreadMonitor {
	private ThreadPoolExecutor threadPoolExecutor;
	private String name;

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