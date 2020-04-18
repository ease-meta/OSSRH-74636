package com.open.cloud.server.tio;

import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.core.intf.PacketListener;

/**
 * @author Leijian
 * @date 2020/4/12
 */
public class PackDemo implements PacketListener {
	@Override
	public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
		System.out.println(1);
	}

	public static void main(String[] args) {
		System.out.println(PackDemo.class.getSimpleName());
	}
}
