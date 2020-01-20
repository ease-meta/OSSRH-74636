package com.open.cloud.common.serialize;

import java.lang.reflect.Type;

public interface Serializer {

	String serialize(Object object);

	<T> T deserialize(String str, Type type);

}
