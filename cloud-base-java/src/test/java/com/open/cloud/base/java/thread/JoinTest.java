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