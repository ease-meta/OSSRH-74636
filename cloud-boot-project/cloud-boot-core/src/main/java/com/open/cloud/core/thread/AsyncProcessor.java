package com.open.cloud.core.thread;

/**
 * @author Administrator
 * @date 2020/10/26
 */
public interface AsyncProcessor extends Runnable {

    @Override
    default void run() {
        execute();
    }

    void execute();
}
