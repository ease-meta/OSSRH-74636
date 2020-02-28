package com.open.cloud.sloars.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class GenericProxyFactory {

	public static <T> T getProxy(Class<T> intf,
								 final T obj) {
		return (T)
				Proxy.newProxyInstance(obj.getClass().getClassLoader(),
						new Class[]{intf}, new InvocationHandler() {
							@Override
							public Object invoke(Object proxy, Method method,
												 Object[] args) throws Throwable {
								return method.invoke(obj, args);
							}
						});
	}
}