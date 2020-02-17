package com.open.cloud.common.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Leijian
 * @date 2020/2/17
 */
public class JavaSerializer implements Serializer {
	@Override
	public String name() {
		return "java";
	}

	@Override
	public byte[] serialize(final Object obj) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(obj);
			return baos.toByteArray();
		}
	}

	@Override
	public <T> T deserialize(final byte[] data, T classOfT) throws IOException {
		if (data == null || data.length == 0) {
			return null;
		}
		try (ByteArrayInputStream bais = new ByteArrayInputStream(data);
			 ObjectInputStream ois = new ObjectInputStream(bais)) {
			return (T) ois.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
