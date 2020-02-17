package com.open.cloud.common.serializer;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class HessianSerializerTest {

	@Test
	void name() {
	}

	@Test
	void serialize() throws IOException {
		HessianSerializer hessianSerializer = new HessianSerializer();
		Person person1 =  new Person("张三",23,false);
		byte[] bytes = hessianSerializer.serialize(person1);
		System.out.println((Person)hessianSerializer.deserialize(bytes,null));
	}

	@Test
	void deserialize() {
	}
}