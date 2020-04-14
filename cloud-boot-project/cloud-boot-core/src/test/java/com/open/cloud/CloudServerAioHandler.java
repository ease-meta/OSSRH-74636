package com.open.cloud;

import io.netty.buffer.ByteBufUtil;
import org.tio.core.ChannelContext;
import org.tio.core.Tio;
import org.tio.core.TioConfig;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;
import org.tio.core.utils.ByteBufferUtils;
import org.tio.server.intf.ServerAioHandler;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author Leijian
 * @date 2020/4/11
 */
public class CloudServerAioHandler implements ServerAioHandler {
	/**
	 * 解码：把接收到的ByteBuffer，解码成应用可以识别的业务消息包
	 * 总的消息结构：消息头 + 消息体
	 * 消息头结构：    8个字节，存储消息体的长度
	 * 消息体结构：
	 */
	@Override
	public Packet decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) throws AioDecodeException {
		if (readableLength < HelloPacket.HEADER_LENGHT) {
			return null;
		}
		byte[] lenghByte = ByteBufferUtils.readBytes(buffer, readableLength);
		/*String message = null;
		byte[] byteMessage = null;
		ByteBuffer buffer1 = null;
		try {
			message = new String(lenghByte, "GBK");
			byteMessage = message.getBytes("GBK");
			buffer1 = ByteBuffer.wrap(byteMessage);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		PackDemo packDemo = new PackDemo();
		HelloPacket helloPacket = new HelloPacket();
		helloPacket.setBlockSend(true);
		helloPacket.setPacketListener(packDemo);
		helloPacket.setBody(lenghByte);
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
		Tio.send(channelContext,packet);
		return byteBuffer;
	}

	@Override
	public void handler(Packet packet, ChannelContext channelContext) throws Exception {
		HelloPacket helloPacket = (HelloPacket)packet;
		byte[] byteMessage = helloPacket.getBody();
		byte[] byteLength = new byte[8];
		byte[] bodyMessage;
		String message = null;
		try {
			message = new String(byteMessage, "GBK");
			System.arraycopy(byteMessage,0,byteLength,0,8);
			int messageLength = Integer.parseInt(new String(byteLength));
			bodyMessage = new byte[messageLength];
			System.arraycopy(byteMessage,8,bodyMessage,0,messageLength);
			message = new String(bodyMessage, "GBK");
			//message处理
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}
}
