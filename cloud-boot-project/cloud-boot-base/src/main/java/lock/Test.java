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

public class Test {

	//value1：线程不安全
	private static int value1 = 0;
	//value2：使用乐观锁
	private static AtomicInteger value2 = new AtomicInteger(0);
	//value3：使用悲观锁
	private static int value3 = 0;

	public static void main(String[] args) throws Exception {
		//开启1000个线程，并执行自增操作
		for (int i = 0; i < 1000; ++i) {
			new Thread(() -> {
				value1++;
				value2.getAndIncrement();
				increaseValue3();

			}).start();
		}
		//打印结果
		Thread.sleep(1000);
		System.out.println("线程不安全：" + value1);
		System.out.println("乐观锁(AtomicInteger)：" + value2);
		System.out.println("悲观锁(synchronized)：" + value3);
	}

	private static synchronized void increaseValue3() {
		value3++;
	}
}