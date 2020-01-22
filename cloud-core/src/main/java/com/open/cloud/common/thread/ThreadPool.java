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
package com.open.cloud.common.thread;

import com.open.cloud.common.base.Ref;
import com.open.cloud.common.config.CoreProperties;
import com.open.cloud.common.exception.BaseException;
import com.open.cloud.common.system.ProcessExitEvent;
import com.open.cloud.common.utils.LogUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import lombok.var;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Leijian
 */
public class ThreadPool {
    static {
        initSystem();
    }

    public static void initSystem() {
        System = new ThreadPool("open.cloud.thread.system",
            ThreadPoolProperties.getThreadPoolMinSize(),
            ThreadPoolProperties.getThreadPoolMaxSize());
    }

    public static ThreadPool   System;

    private ThreadPoolExecutor threadPool;

    @Getter
    @Setter
    private boolean            checkHealth = true;

    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    @Getter
    private ThreadMonitor threadMonitor;

    @Getter
    private String        name;

    public ThreadPool(String name, int threadPoolMinSize, int threadPoolMaxSize) {
        this.name = name;
        threadPool = new ThreadPoolExecutor(threadPoolMinSize, threadPoolMaxSize, 60L,
            TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new SystemThreadPoolFactory());
        threadMonitor = new ThreadMonitor(this.name, threadPool);
    }

    private void checkHealth() {
        if (checkHealth && threadPool.getMaximumPoolSize() <= threadPool.getPoolSize()
            && threadPool.getQueue().size() > 0) {
            LogUtils.warn(ThreadPool.class, CoreProperties.Project,
                "线程池已满,任务开始出现排队,请考虑设置threadpool.max,当前:" + threadPool.getMaximumPoolSize());
        }
    }

    public <T> Future<T> submit(String taskName, Callable<T> task) {
		checkHealth();
		return threadMonitor.hook().run(taskName, () -> threadPool.submit(task));
	}

    public Future<?> submit(String taskName, Runnable task) {
		checkHealth();
		return threadMonitor.hook().run(taskName, () -> threadPool.submit(task));
	}

    public boolean isShutdown() {
        return threadPool.isShutdown();
    }

    public void shutdown() {
        threadPool.shutdown();
    }

    static {
        //JVM 停止或重启时，关闭线程池释
        /*ProcessExitEvent.register(() -> {
        	try {
        		System.shutdown();
        	} catch (Exception e) {
        		LogUtils.error(ThreadPool.class, CoreProperties.Project, "关闭SystemThreadPool时出错", e);
        	}
        }, Integer.MAX_VALUE, false);*/
        ProcessExitEvent.register(new com.open.cloud.common.base.Callable.Action0() {
            @Override
            public void invoke() {
                try {
                    System.shutdown();
                } catch (Exception e) {
                    LogUtils.error(ThreadPool.class, CoreProperties.Project,
                        "关闭SystemThreadPool时出错", e);
                }
            }
        }, Integer.MAX_VALUE, false);

    }

    /**
     * 任务拆分多个小任务分批并行处理，并行处理完一批再并行处理下一批。
     * 在抛出错误的时候有问题，未修复，仅试验，不要用这个方法。
     *
     * @param taskName
     * @param parallelCount
     * @param array
     * @param action
     * @param <T>
     */
    public <T> void parallelFor(String taskName, int parallelCount, List<T> array, final com.open.cloud.common.base.Callable.Action1<T> action) {
		checkHealth();
		threadMonitor.hook().run(taskName, () -> {
			var parallelCount2 = parallelCount;
			if (parallelCount2 > array.size()) {
				parallelCount2 = array.size();
			}
			//任务队列
			Queue<T> queueTasks = new LinkedList();
			queueTasks.addAll(array);

			while (queueTasks.size() > 0) {
				//运行任务列表
				final List<T> runningTasks = new ArrayList<>(parallelCount2);
				T task;
				for (int i = 0; i < parallelCount2; i++) {
					if ((task = queueTasks.poll()) != null) {
						runningTasks.add(task);
					} else {
						break;
					}
				}

				final CountDownLatch latch = new CountDownLatch(runningTasks.size());
				List<Future<?>> result = new ArrayList<>(parallelCount2);
				for (val obj : runningTasks) {
					Future<?> future = threadPool.submit(() -> {
						try {
							action.invoke(obj);
						} catch (Exception exp) {
							throw exp;
						} finally {
							latch.countDown();
						}
					});
					result.add(future);
				}

				try {
					latch.await();
				} catch (InterruptedException exp) {
					LogUtils.error(ThreadPool.class, CoreProperties.Project, "parallelFor 任务计数异常", exp);
				}
				for (Future<?> f : result) {
					try {
						f.get();
					} catch (Exception exp) {
						throw new BaseException("parallelFor并行执行出错", exp);
					}
				}
			}
			return 1;
		});

	}

    /**
     * 任务使用固定并行大小处理任务,直到所有任务处理完毕。
     *
     * @param taskName
     * @param parallelCount
     * @param array
     * @param action
     * @param <T>
     */
    public <T> void parallelFor2(String taskName, int parallelCount, List<T> array, final com.open.cloud.common.base.Callable.Action1<T> action) {
		checkHealth();
		threadMonitor.hook().run(taskName, () -> {
			var parallelCount2 = parallelCount;
			if (parallelCount2 > array.size()) {
				parallelCount2 = array.size();
			}
			//任务队列
			Queue<T> queueTasks = new LinkedList();
			queueTasks.addAll(array);

			if (queueTasks.size() > 0) {
				final CountDownLatch latch = new CountDownLatch(parallelCount2);
				Object lock = new Object();
				Ref<Exception> exceptionRef = new Ref<>(null);
				for (int i = 0; i < parallelCount2; i++) {
					threadPool.submit(() -> {
						while (true) {
							T task;
							synchronized (lock) {
								task = queueTasks.poll();
							}
							if (task != null && exceptionRef.isNull()) {
								try {
									action.invoke(task);
								} catch (Exception exp) {
									latch.countDown();
									exceptionRef.setData(exp);
									break;
								}
							} else {
								latch.countDown();
								break;
							}
						}
						return;
					});
				}

				try {
					latch.await();
				} catch (InterruptedException exp) {
					LogUtils.error(ThreadPool.class, CoreProperties.Project, "parallelFor 任务计数异常", exp);
				}
				if (!exceptionRef.isNull()) {
					throw new BaseException("parallelFor 并行执行出错", exceptionRef.getData());
				}
			}
			return 1;
		});
	}

    static class SystemThreadPoolFactory implements ThreadFactory {
        private ThreadFactory factory = Executors.defaultThreadFactory();

        @Override
        public Thread newThread(Runnable r) {
            Thread t = factory.newThread(r);
            //后台线程模式
            t.setDaemon(true);
            return t;
        }
    }

}
