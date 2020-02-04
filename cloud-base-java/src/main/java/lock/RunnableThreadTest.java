/*
package lock;

import java.util.concurrent.atomic.AtomicInteger;

public class RunnableThreadTest {

	//value1：线程不安全
	private static int value1 = 0;
	//value2：使用乐观锁
	private static AtomicInteger value2 = new AtomicInteger(0);
	//value3：使用悲观锁
	private static int value3 = 0;

	private static synchronized void increaseValue3() {
		value3++;
	}


	public void testStatic() {
		// 重用静态方法中的代码【使用方法引用】
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 2) {
				new Thread(RunnableThreadTest::increaseValue3, "线程1").start();
				new Thread(value3-value3++, "线程1").start();
			}
		}
	}
}
*/
