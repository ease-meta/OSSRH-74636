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

public class WaitDemo {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
		Thread notifyThread = new Thread(() -> {
			synchronized (lock) {
				System.out.println("开始：Notify");
				lock.notify();
				System.out.println("结束：Notify");
			}
		});

		Thread waitThread = new Thread(() -> {
			synchronized (lock) {
				System.out.println("开始：Wait");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					System.out.println("结束：Wait");
				}
			}

		});

		waitThread.start();
		//waitThread.join();
		notifyThread.start();
	}
}

class NotifyThread implements Runnable {

    private Object lock;

    public NotifyThread(Object lock) {
        this.lock = lock;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("开始：" + this.getClass().getName());
            lock.notify();
            System.out.println("结束：" + this.getClass().getName());
        }
    }

}

class WaitThread implements Runnable {

    private Object lock;

    public WaitThread(Object lock) {
        this.lock = lock;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        synchronized (lock) {
            System.out.println("开始：" + this.getClass().getName());
            try {
                lock.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                System.out.println("结束：" + this.getClass().getName());
            }
        }
    }

}