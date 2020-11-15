package com.open.cloud.core.log;


/**
 * 全局日志工厂类<br>
 * 用于减少日志工厂创建，减少日志库探测
 *
 * @author looly
 * @since 4.0.3
 */
public class GlobalLogFactory {
	private static volatile LogFactory currentLogFactory;
	private static final Object lock = new Object();

	/**
	 * 获取单例日志工厂类，如果不存在创建之
	 *
	 * @return 当前使用的日志工厂
	 */
	public static LogFactory get() {
		if (null == currentLogFactory) {
			synchronized (lock) {
				if (null == currentLogFactory) {
					currentLogFactory = LogFactory.create();
				}
			}
		}
		return currentLogFactory;
	}
}
