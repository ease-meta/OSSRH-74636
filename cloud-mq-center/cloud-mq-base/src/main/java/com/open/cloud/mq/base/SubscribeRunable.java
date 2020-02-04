package com.open.cloud.mq.base;
/**
 * @author Leijian
 * @date   2020/2/3
 */
public interface SubscribeRunable<T> {
	void run(Message<T> msg);
}