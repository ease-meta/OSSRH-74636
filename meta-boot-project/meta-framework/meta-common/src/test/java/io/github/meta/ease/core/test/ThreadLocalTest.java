package io.github.meta.ease.core.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalTest {

    private static ThreadLocal tl = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        tl.set(1);
        System.out.println(String.format("当前线程名称: %s, main方法内获取线程内数据为: %s",
                Thread.currentThread().getName(), tl.get()));
        fc();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {

                if (finalI % 2 == 0) {
                    tl.set(finalI);
                }
                ThreadLocalTest.fc();
            });
        }
        new Thread(ThreadLocalTest::fc).start();
    }

    private static void fc() {
        System.out.println(String.format("当前线程名称: %s, fc方法内获取线程内数据为: %s", Thread.currentThread()
                .getName(), tl.get()));
    }

}
