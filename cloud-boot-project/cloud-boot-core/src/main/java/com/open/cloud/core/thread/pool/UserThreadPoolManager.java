package com.open.cloud.core.thread.pool;


import com.open.cloud.core.thread.AsyncProcessor;
import org.springframework.util.Assert;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The type User thread pool manager.
 */
public final class UserThreadPoolManager {

    /**
     * 用户自定义的业务线程池，可以给不同的接口指定不同的业务线程池
     */
    private static ConcurrentMap<String, UserThreadPool> userThreadMap = null;

    private UserThreadPoolManager() {

    }

    /**
     * 是否有自定义线程池
     *
     * @return 是否 boolean
     */
    public static boolean hasUserThread() {
        return userThreadMap != null && userThreadMap.size() > 0;
    }

    /**
     * 给某个服务分配到独立的线程池
     *
     * @param service        服务唯一名
     * @param userThreadPool 自定义线程池
     */
    public static synchronized void registerUserThread(String service, UserThreadPool userThreadPool) {
        if (userThreadMap == null) {
            userThreadMap = new ConcurrentHashMap<>();
        }
        userThreadMap.put(service, userThreadPool);
    }

    /**
     * 给某个服务取消分配到独立线程池
     *
     * @param service 服务唯一名
     */
    @SuppressWarnings("squid:UnusedPrivateMethod")
    public static synchronized void unRegisterUserThread(String service) {
        if (userThreadMap != null) {
            userThreadMap.remove(service);
        }
    }

    /**
     * 得到用户线程池
     *
     * @param service 服务唯一名
     * @return 用户自定义线程池
     */
    private static UserThreadPool getUserThread(String service) {
        return userThreadMap == null ? null : userThreadMap.get(service) == null ? userThreadMap.get("CbsUserProcessor") : userThreadMap.get(service);
    }

    /**
     * Run async.
     *
     * @param service  the service
     * @param runnable the runnable
     */
    public static void runAsync(String service, AsyncProcessor runnable) {
        UserThreadPool userThreadPool = getUserThread(service);
        Assert.notNull(userThreadPool, "userThreadPool can not null");
        CompletableFuture.runAsync(runnable, userThreadPool.getExecutor());
    }

}
