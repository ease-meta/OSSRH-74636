package com.open.cloud.test.javassist;

import javassist.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws NotFoundException,
            CannotCompileException, InstantiationException, IllegalAccessException, ClassNotFoundException {

        //~加上报错，和类加载有关系，这里new的话会把Hello类加载，之后再加载的话就会报错
        //Hello hell=new Hello();
        //hell.sayHello();
        //~


        CtClass ctClass = ClassPool.getDefault().getCtClass("com.open.cloud.test.javassist.Hello");


        CtMethod ctMethod = ctClass.getDeclaredMethod("sayHello");
        ctMethod.setBody("System.out.println(\"hi\");");


        Class ch = ctClass.toClass();

        Hello h = (Hello) ch.newInstance();
        h.sayHello();

        Hello hel = new Hello();
        hel.sayHello();

    }
}