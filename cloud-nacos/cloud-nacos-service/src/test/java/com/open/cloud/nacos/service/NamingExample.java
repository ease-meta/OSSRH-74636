package com.open.cloud.nacos.service;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;

import java.util.Properties;

public class NamingExample {

	public static void main(String[] args) throws NacosException {

		Properties properties = new Properties();
		properties.setProperty("serverAddr", System.getProperty("serverAddr"));
		properties.setProperty("namespace", System.getProperty("namespace"));

		NamingService naming = NamingFactory.createNamingService(properties);

		naming.registerInstance("cloud-nacos-service", "11.11.11.11", 8888, "TEST1");

		naming.registerInstance("cloud-nacos-service", "2.2.2.2", 9999, "DEFAULT");

		System.out.println(naming.getAllInstances("cloud-nacos-service"));

		naming.deregisterInstance("cloud-nacos-service", "2.2.2.2", 9999, "DEFAULT");

		System.out.println(naming.getAllInstances("cloud-nacos-service"));

		naming.subscribe("cloud-nacos-service", new EventListener() {
			@Override
			public void onEvent(Event event) {
				System.out.println(((NamingEvent) event).getServiceName());
				System.out.println(((NamingEvent) event).getInstances());
			}
		});
	}
}