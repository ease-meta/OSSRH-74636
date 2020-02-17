package com.open.cloud.common.serializer;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import org.springframework.core.codec.CodecException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author Leijian
 * @date 2020/2/17
 */
public class HessianSerializer implements Serializer {
	public static final SerializerFactory INSTANCE = new SerializerFactory();

	@Override
	public String name() {
		return "hessian";
	}

	@Override
	public byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		Hessian2Output output = new Hessian2Output(byteArray);
		output.setSerializerFactory(INSTANCE);
		try {
			output.writeObject(obj);
			output.close();
		} catch (IOException e) {
			throw new CodecException("IOException occurred when Hessian serializer encode!", e);
		}
		return byteArray.toByteArray();
	}

	@Override
	public <T> T deserialize(byte[] data, T classOfT) throws IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
		// Hessian的反序列化读取对象
		Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
		return (T) hessian2Input.readObject();
	}
}
