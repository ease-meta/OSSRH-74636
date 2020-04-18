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
		CloudServerAioHandler cloudServerAioHandler = new CloudServerAioHandler();
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
