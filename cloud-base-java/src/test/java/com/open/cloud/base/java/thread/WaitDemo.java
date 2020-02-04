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