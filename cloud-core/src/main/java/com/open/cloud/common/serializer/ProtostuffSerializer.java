/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
