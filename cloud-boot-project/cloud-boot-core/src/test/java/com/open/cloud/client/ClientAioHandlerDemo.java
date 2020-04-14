package com.open.cloud.client;

import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * @author Leijian
 * @date 2020/4/12
 */
public class ClientAioHandlerDemo implements ClientAioHandler {
	@Override
	public Packet heartbeatPacket(ChannelContext channelContext) {
		return null;
	}

	@Override
	public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
		return null;
	}

	@Override
	public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
		return null;
	}

	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {

	}
}
