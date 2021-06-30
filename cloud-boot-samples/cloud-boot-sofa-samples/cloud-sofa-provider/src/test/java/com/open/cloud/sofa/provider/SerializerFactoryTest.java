package com.open.cloud.sofa.provider;

import com.alipay.sofa.rpc.codec.Serializer;
import com.alipay.sofa.rpc.codec.SerializerFactory;
import com.alipay.sofa.rpc.transport.AbstractByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/6/23 19:57
 */
public class SerializerFactoryTest {

    private static final Logger logger = LoggerFactory.getLogger(SerializerFactoryTest.class);

    public static void main(String[] args) {
        Serializer serializer = SerializerFactory.getSerializer("hessian2");
        Student student = new Student();
        Student.Body body = new Student.Body();
        body.setAge(123);
        student.setBody(body);
        AbstractByteBuf abstractByteBuf = serializer.encode(student, null);

        Student2 object = (Student2) serializer.decode(abstractByteBuf, Student2.class, null);

        System.out.println(object);
    }

}
