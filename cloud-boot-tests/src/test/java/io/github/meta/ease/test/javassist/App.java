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
package io.github.meta.ease.test.javassist;

import org.apache.ibatis.javassist.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws NotFoundException, CannotCompileException,
            InstantiationException, IllegalAccessException,
            ClassNotFoundException {

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