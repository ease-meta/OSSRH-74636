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
package com.open.cloud.base.java.thread;

class ThreadA extends Thread {

    private static Object lock = new Object();

    public ThreadA(String name) {
        super(name);
    }

    /*@Override
    public synchronized void run() {
    	for (int i = 0; i < 10; i++) {
    		System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
    		if (i % 4 == 0) {
    			Thread.yield();
    		}
    	}
    }*/

    public void run() {
        synchronized (lock) {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%s [%d]:%d\n", this.getName(), this.getPriority(), i);
                if (i % 4 == 0) {
                    Thread.yield();
                }
            }
        }
    }

}

public class YieldTest {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        ThreadA t2 = new ThreadA("t2");
        t1.start();
        t2.start();
    }
}