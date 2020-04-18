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
package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @author shadow
 * @Date 2016年8月6日下午9:17:48
 * @Fun 动态代理：使用jdk提供的InvocationHandler，反射调用被代理对象的方法，结合java.reflect.Proxy产生代理对象<br/>
 * <p>
 * 动态传入被代理对象构造InvocationHandler，在handler中的invoke时可以增强被代理对象的方法的功能<br/>
 * 或者说：（面向切面：）在什么地方（连接点），执行什么行为（通知）<br/>
 * GamePlayerProxy3中是方法名为login时通知开始时间，upgrade时通知结束时间。<br/>
 */

public class GamePlayerProxy3 {
	private IGamePlayer gamePlayer;

	public GamePlayerProxy3(IGamePlayer gamePlayer) {
		// TODO Auto-generated constructor stub
		this.gamePlayer = gamePlayer;
	}

	public IGamePlayer getProxy() {
		return (IGamePlayer) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				new Class[]{IGamePlayer.class}, new MyInvocationHandler());
	}

	private class MyInvocationHandler implements InvocationHandler {

		@SuppressWarnings("deprecation")
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// TODO Auto-generated method stub
			if (method.getName().equals("login")) {
				System.out.println("登录时间是：" + new Date().toLocaleString());
			} else if (method.getName().equals("upgrade")) {
				System.out.println("升级时间是：" + new Date().toLocaleString());
			}

			method.invoke(gamePlayer, args);
			return null;
		}

	}
}
