package com.open.cloud.client;

import org.tio.client.ClientTioConfig;
import org.tio.client.DefaultClientAioListener;
import org.tio.client.TioClient;

import java.io.IOException;

/**
 * @author Leijian
 * @date 2020/4/12
 */
public class TioClientDemo {

	public static void main(String[] args) throws IOException {
		DefaultClientAioListener defaultClientAioListener = new DefaultClientAioListener();
		ClientAioHandlerDemo clientAioHandlerDemo = new ClientAioHandlerDemo();
		ClientTioConfig clientTioConfig = new ClientTioConfig(clientAioHandlerDemo,defaultClientAioListener);

		TioClient tioClient = new TioClient(clientTioConfig);
		//tioClient.
	}
}
