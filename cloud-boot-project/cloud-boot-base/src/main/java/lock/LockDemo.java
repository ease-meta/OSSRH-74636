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
package lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    //悲观锁
    private ReentrantLock lock          = new ReentrantLock();
    //乐观锁的使用方式
    private AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        int i = 128;
        byte b = (byte) i;
        System.out.println(b);

    }

    //悲观锁
    public synchronized void testMethod() {

    }

    public void modify() {
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }

}
