package com.open.cloud.server.tio;

import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.core.utils.ByteBufferUtils;
import org.tio.server.intf.ServerAioHandler;

import java.nio.ByteBuffer;

/**
 * @author Leijian
 * @date 2020/4/11
 */
public class CloudServerAioHandler implements ServerAioHandler {
	private static final String EMPTY_RESPONSE = "Galaxy server is starting!";
	private static final String DEFAULT_SERVICE_INVOKER_ID = "serviceInvoker";

	/**
	 * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
	 * 总的消息结构：消息头 + 消息体
	 * 消息头结构：    8个字节，存储消息体的长度
	 * 消息体结构：
	 */
	@Override
	public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
		if (readableLength < MessagePacket.HEADER_LENGHT) {
			return null;
		}
		//一次解析出所有包
		MessagePacket helloPacket = new MessagePacket();
		byte[] contentBytes = ByteBufferUtils.readBytes(buffer, readableLength);
		helloPacket.setBody(contentBytes);
		return helloPacket;
	}

	/**
	 * 编码：把业务消息包编码为可以发送的ByteBuffer
	 * 总的消息结构：消息头 + 消息体
	 * 消息头结构：    4个字节，存储消息体的长度
	 * 消息体结构：   对象的json串的byte[]
	 */
	@Override
	public ByteBuffer encode(Packet packet, TioConfig tioConfig, ChannelContext channelContext) {
		String message = "你哈奥。客户端";
		ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
		packet.setPreEncodedByteBuffer(byteBuffer);
		return byteBuffer;
	}

	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		MessagePacket messagePacket = (MessagePacket) packet;
		byte[] contentBytes = messagePacket.getBody();
		byte[] lengthBytes = new byte[8];
		System.arraycopy(contentBytes, 0, lengthBytes, 0, 8);
		String length = new String(lengthBytes, MessagePacket.CHARSET);
		int contentLength = Integer.parseInt(length);
		byte[] bodyBytes = new byte[contentLength];
		System.arraycopy(contentBytes, 8, bodyBytes, 0, contentLength);
		String inMsg = new String(bodyBytes, MessagePacket.CHARSET);
		Tio.send(channelContext, packet);
	}


}
