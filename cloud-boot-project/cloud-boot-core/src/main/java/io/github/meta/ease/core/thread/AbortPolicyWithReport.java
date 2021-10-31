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
package io.github.meta.ease.core.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The type Abort policy with report.
 *
 * @author Leijian
 * @date 2020 /5/23 22:25
 */
public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {

    private static final Logger logger = LoggerFactory.getLogger(AbortPolicyWithReport.class);

    private final String threadName;

    /**
     * Instantiates a new Abort policy with report.
     *
     * @param threadName the thread name
     */
    public AbortPolicyWithReport(String threadName) {
        this.threadName = threadName;
    }

    /**
     * Rejected execution.
     *
     * @param r the r
     * @param e the e
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        String msg = String
                .format(
                        "Thread pool is EXHAUSTED!"
                                + " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), Task: %d (completed: %d),"
                                + " Executor status:(isShutdown:%s, isTerminated:%s, isTerminating:%s)!",
                        threadName, e.getPoolSize(), e.getActiveCount(), e.getCorePoolSize(),
                        e.getMaximumPoolSize(), e.getLargestPoolSize(), e.getTaskCount(),
                        e.getCompletedTaskCount(), e.isShutdown(), e.isTerminated(), e.isTerminating());
        logger.error(msg);
        throw new RejectedExecutionException(msg);
    }

}
