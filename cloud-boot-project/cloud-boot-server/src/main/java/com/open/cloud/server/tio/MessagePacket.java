package com.open.cloud.server.tio;

import org.tio.core.intf.Packet;

/**
 * @author Leijian
 * @date 2020/4/17
 */
public class MessagePacket extends Packet {

	private static final long serialVersionUID = -172060606924066412L;
	public static final int HEADER_LENGHT = 8;//消息头的长度
	public static final String CHARSET = "GBK";
	private byte[] body;
	private int length;

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	@Override
	public void setBlockSend(boolean isBlockSend) {
		super.setBlockSend(isBlockSend);
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
