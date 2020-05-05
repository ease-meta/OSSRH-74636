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

public class JoinTest {
    public static void main(String[] args) throws InterruptedException {
		Thread AliThread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
		}, "Ali");
		Thread HSFThread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
		}, "HSF");
		Thread TomcatThread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName());
		}, "Tomcat");
		/**
		 * join的意思是使得放弃当前线程的执行，并返回对应的线程，例如下面代码的意思就是：
		 * 程序在main线程中调用Ali线程的join方法，则main线程放弃cpu控制权，并返回Ali。线程执行的顺序为Ali->Tomcat-HSF
		 */
		AliThread.start();
		AliThread.join();
		TomcatThread.start();
		TomcatThread.join();
		HSFThread.start();
	}
}