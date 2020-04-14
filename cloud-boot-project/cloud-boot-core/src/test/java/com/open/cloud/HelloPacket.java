package com.open.cloud;

import org.tio.core.intf.Packet;
import org.tio.core.intf.PacketListener;

/**
 * @author tanyaowu
 */
public class HelloPacket extends Packet {
	private static final long serialVersionUID = -172060606924066412L;
	public static final int HEADER_LENGHT = 8;//消息头的长度
	public static final String CHARSET = "utf-8";
	private byte[] body;

	@Override
	public void setBlockSend(boolean isBlockSend) {
		super.setBlockSend(isBlockSend);
	}

	@Override
	public void setPacketListener(PacketListener packetListener) {
		super.setPacketListener(packetListener);
	}

	/**
	 * @return the body
	 */
	public byte[] getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(byte[] body) {
		this.body = body;
	}
}
