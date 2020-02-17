package com.open.cloud.common.serializer;


import org.apache.poi.ss.formula.functions.T;

import java.io.IOException;

/**
 * @author Leijian
 * @date 2020/2/17
 */
/*public class ProtostuffSerializer implements Serializer {
	@Override
	public String name() {
		return null;
	}

	@Override
	public byte[] serialize(Object obj) throws IOException {
		RuntimeSchema<T> schema;
		LinkedBuffer buffer = null;
		byte[] result;
		try {
			schema = RuntimeSchema.createFrom((Class<T>) source.getClass());
			buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
			result = ProtostuffIOUtil.toByteArray(source, schema, buffer);
		} catch (Exception e) {
			throw new RuntimeException("serialize exception");
		} finally {
			if (buffer != null) {
				buffer.clear();
			}
		}

		return result;
	}

	@Override
	public <T> T deserialize(byte[] data, String classOfT) throws IOException {
		return null;
	}
}*/
