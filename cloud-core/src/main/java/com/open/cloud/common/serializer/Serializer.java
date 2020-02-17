package com.open.cloud.common.serializer;

import java.io.IOException;

/**
 * @author Leijian
 * @date 2020/2/17
 */
public interface Serializer {

	 String name();

	 byte[] serialize(final Object obj) throws IOException;

	<T> T deserialize(final byte[] data, T classOfT) throws IOException ;
}
