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
package io.github.meta.ease.core.test;

public class ThreadLocalTest {
    private static ThreadLocal tl = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        tl.set(1);
        System.out.println(String.format("当前线程名称: %s, main方法内获取线程内数据为: %s",
                Thread.currentThread().getName(), tl.get()));
        fc();
        new Thread(ThreadLocalTest::fc).start();
    }

    private static void fc() {
        System.out.println(String.format("当前线程名称: %s, fc方法内获取线程内数据为: %s", Thread.currentThread()
                .getName(), tl.get()));
    }
}
