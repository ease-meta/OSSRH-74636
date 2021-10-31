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
package io.github.meta.ease.test.web.controler;

import io.github.meta.ease.test.javassist.Hello;
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
    public void test() throws InvocationTargetException, IllegalAccessException,
            NoSuchMethodException {
        /* Method method = Hello.class.getMethod("sys",String.class);
        //SpringEL 表达式获取参数
        method.invoke(new Hello(),"123");*/
        Method method = Hello.class.getMethod("sys", String.class);
        method.invoke(new Hello(), "123");
    }

    @Test
    public void test1() throws NotFoundException, IOException, CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point");
        cc.writeFile("D:/");
    }

}