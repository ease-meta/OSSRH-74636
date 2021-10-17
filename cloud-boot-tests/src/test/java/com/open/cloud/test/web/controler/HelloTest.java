package com.open.cloud.test.web.controler;


import com.open.cloud.test.javassist.Hello;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/10/16 13:12
 */
public class HelloTest {

    private static final ClassPool POOL = new ClassPool();

    @Test
    public void test() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
         /* Method method = Hello.class.getMethod("sys",String.class);
        //SpringEL 表达式获取参数
        method.invoke(new Hello(),"123");*/
        Method method = com.open.cloud.test.javassist.Hello.class.getMethod("sys", String.class);
        method.invoke(new Hello(), "123");
    }

    @Test
    public void test1() throws NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point");
        cc.writeFile("D:/");
    }

}