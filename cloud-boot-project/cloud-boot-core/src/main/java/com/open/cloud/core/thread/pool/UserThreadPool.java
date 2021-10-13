package com.open.cloud.core.thread.pool;

import com.open.cloud.core.thread.NamedThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The type User thread pool.
 *
 * @author Leijian
 * @date 2020 /5/23 22:27
 */
public class UserThreadPool {

    /**
     * The constant CBS_USER_PROCESSOR.
     */
    public static final String CBS_USER_PROCESSOR = "CbsUserProcessor";
    private static final Logger logger = LoggerFactory.getLogger(UserThreadPool.class);
    /**
     * 线程池
     */
    transient volatile ThreadPoolExecutor executor;
    /**
     * 服务名，用于线程隔离
     */
    private List<String> service = new LinkedList<>();
    private int order;
    /**
     * 核心线程池
     *
     * @see ThreadPoolExecutor#corePoolSize
     */
    private int corePoolSize = 10;
    /**
     * 最大线程池
     *
     * @see ThreadPoolExecutor#maximumPoolSize
     */
    private int maximumPoolSize = 100;
    /**
     * 线程回收时间（毫秒）
     *
     * @see ThreadPoolExecutor#keepAliveTime
     */
    private int keepAliveTime = 300000;
    /**
     * 优雅停机时间（毫秒）
     *
     * @see UserThreadPool#timeout
     */
    private long timeout = 30;
    /**
     * 队列大小
     *
     * @see ThreadPoolExecutor#getQueue()
     */
    private int queueSize = 0;
    /**
     * 线程名字
     *
     * @see ThreadPoolExecutor#threadFactory#threadPoolName
     */
    private String threadPoolName = CBS_USER_PROCESSOR;
    /**
     * 是否关闭核心线程池
     *
     * @see ThreadPoolExecutor#allowCoreThreadTimeOut
     */
    private boolean allowCoreThreadTimeOut;
    /**
     * 是否初始化核心线程池
     *
     * @see ThreadPoolExecutor#prestartAllCoreThreads
     */
    private boolean prestartAllCoreThreads;

    /**
     * 初始化线程池
     */
    public void init() {
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
                ThreadPoolUtils.buildQueue(queueSize), new NamedThreadFactory(threadPoolName));
        if (allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }
        if (prestartAllCoreThreads) {
            executor.prestartAllCoreThreads();
        }
        //注册钩子关闭
       /* ProcessExitEvent.register(() -> {
            try {
                executor.shutdown();
                if (!executor.awaitTermination(this.timeout, TimeUnit.SECONDS)) {
                    logger.info("UserThreadPool shutdown immediately due to wait timeout.");
                    executor.shutdownNow();
                }
                logger.info("UserThreadPool shutdown complete.");
            } catch (Exception e) {
                logger.error("ShutdownHook for UserThreadPool error.{}", e.getMessage());
            }
        }, Integer.MAX_VALUE, false);*/
    }

    /**
     * 销毁线程池.
     */
    public void destroy() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    /**
     * Gets core pool size.
     *
     * @return the core pool size
     */
    public int getCorePoolSize() {
        return corePoolSize;
    }

    /**
     * Sets core pool size.
     *
     * @param corePoolSize the core pool size
     * @return the core pool size
     */
    public UserThreadPool setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    /**
     * Gets maximum pool size.
     *
     * @return the maximum pool size
     */
    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    /**
     * Sets maximum pool size.
     *
     * @param maximumPoolSize the maximum pool size
     * @return the maximum pool size
     */
    public UserThreadPool setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    /**
     * Gets queue size.
     *
     * @return the queue size
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * Sets queue size.
     *
     * @param queueSize the queue size
     * @return the queue size
     */
    public UserThreadPool setQueueSize(int queueSize) {
        this.queueSize = queueSize;
        return this;
    }

    /**
     * Gets thread pool name.
     *
     * @return the thread pool name
     */
    public String getThreadPoolName() {
        return threadPoolName;
    }

    /**
     * Sets thread pool name.
     *
     * @param threadPoolName the thread pool name
     * @return the thread pool name
     */
    public UserThreadPool setThreadPoolName(String threadPoolName) {
        this.threadPoolName = threadPoolName;
        return this;
    }

    /**
     * Is allow core thread time out boolean.
     *
     * @return the boolean
     */
    public boolean isAllowCoreThreadTimeOut() {
        return allowCoreThreadTimeOut;
    }

    /**
     * Sets allow core thread time out.
     *
     * @param allowCoreThreadTimeOut the allow core thread time out
     * @return the allow core thread time out
     */
    public UserThreadPool setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        return this;
    }

    /**
     * Is prestart all core threads boolean.
     *
     * @return the boolean
     */
    public boolean isPrestartAllCoreThreads() {
        return prestartAllCoreThreads;
    }

    /**
     * Sets prestart all core threads.
     *
     * @param prestartAllCoreThreads the prestart all core threads
     * @return the prestart all core threads
     */
    public UserThreadPool setPrestartAllCoreThreads(boolean prestartAllCoreThreads) {
        this.prestartAllCoreThreads = prestartAllCoreThreads;
        return this;
    }

    /**
     * Gets keep alive time.
     *
     * @return the keep alive time
     */
    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    /**
     * Sets keep alive time.
     *
     * @param keepAliveTime the keep alive time
     * @return the keep alive time
     */
    public UserThreadPool setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    /**
     * Gets executor.
     *
     * @return the executor
     */
    public ThreadPoolExecutor getExecutor() {
        if (executor == null) {
            synchronized (this) {
                if (executor == null) {
                    init();
                }
            }
        }
        return executor;
    }

    /**
     * Gets service.
     *
     * @return the service
     */
    public List<String> getService() {
        return service;
    }

    /**
     * Sets service.
     *
     * @param service the service
     */
    public void setService(List<String> service) {
        this.service = service;
    }

    /**
     * Gets order.
     *
     * @return the order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets order.
     *
     * @param order the order
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Gets timeout.
     *
     * @return the timeout
     */
    public long getTimeout() {
        return timeout;
    }

    /**
     * Sets timeout.
     *
     * @param timeout the timeout
     */
    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserThreadPool that = (UserThreadPool) o;
        return Objects.equals(getThreadPoolName(), that.getThreadPoolName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getThreadPoolName());
    }
}
