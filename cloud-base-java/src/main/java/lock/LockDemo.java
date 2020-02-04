package lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

	//悲观锁
	public synchronized void testMethod() {

	}

	//悲观锁
	private ReentrantLock lock = new ReentrantLock();

	//乐观锁的使用方式
	private AtomicInteger atomicInteger = new AtomicInteger();

	public void modify() {
		try {
			lock.lock();
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		Thread thread = new Thread();

	}

}
