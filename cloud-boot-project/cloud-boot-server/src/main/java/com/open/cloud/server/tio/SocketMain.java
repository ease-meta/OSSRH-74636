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
package com.open.cloud.server.tio;

import org.tio.server.ServerTioConfig;
import org.tio.server.TioServer;

import java.io.IOException;
import java.nio.ByteOrder;

/**
 * @author Leijian
 * @date 2020/4/11
 */
public class SocketMain {
	private static final String GROUP_CONTEXT_NAME = "tio-server-spring-boot-starter";

	public static void main(String[] args) throws IOException {
		CloudServerAioHandler cloudServerAioHandler = new CloudServerAioHandler("");
		CloudListen serverAioListener = new CloudListen();
		ServerTioConfig serverTioConfig = new ServerTioConfig(GROUP_CONTEXT_NAME, cloudServerAioHandler, serverAioListener);
		serverTioConfig.setServerAioListener(serverAioListener);
		serverTioConfig.setShortConnection(true);
		serverTioConfig.setByteOrder(ByteOrder.nativeOrder());
		TioServer tioServer = new TioServer(serverTioConfig);
		tioServer.setCheckLastVersion(false);
		tioServer.start("127.0.0.1", 9010);
	}
}
