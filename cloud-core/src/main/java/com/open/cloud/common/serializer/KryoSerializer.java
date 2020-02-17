package com.open.cloud.common.serializer;

import java.io.IOException;

/**
 * @author Leijian
 * @date 2020/2/17
 */
public class KryoSerializer implements Serializer {
	@Override
	public String name() {
		return "kryo";
	}

	@Override
	public byte[] serialize(Object obj) throws IOException {
		return new byte[0];
	}

	@Override
	public <T> T deserialize(byte[] data, T classOfT) throws IOException {
		return null;
	}

}
