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
package seq;

import com.jd.platform.async.executor.Async;
import com.jd.platform.async.executor.timer.SystemClock;
import com.jd.platform.async.wrapper.WorkerWrapper;

import java.util.concurrent.ExecutionException;

/**
 * 串行测试
 *
 * @author wuweifeng wrote on 2019-11-20.
 */
@SuppressWarnings("Duplicates")
public class TestSequentialTimeout {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        testFirstTimeout();
    }

    /**
     * begin-1576719450476
     * callback worker0 failure--1576719451338----worker0--default-threadName:main
     * callback worker1 failure--1576719451338----worker1--default-threadName:main
     * callback worker2 failure--1576719451338----worker2--default-threadName:main
     * end-1576719451338
     * cost-862
     */
    private static void testFirstTimeout() throws ExecutionException, InterruptedException {
        SeqWorker1 w1 = new SeqWorker1();
        SeqWorker2 w2 = new SeqWorker2();
        SeqTimeoutWorker t = new SeqTimeoutWorker();

        WorkerWrapper<String, String> workerWrapper2 = new WorkerWrapper.Builder<String, String>()
                .worker(w2).callback(w2).param("2").build();

        WorkerWrapper<String, String> workerWrapper1 = new WorkerWrapper.Builder<String, String>()
                .worker(w1).callback(w1).param("1").next(workerWrapper2).build();

        //2在1后面串行
        //T会超时
        WorkerWrapper<String, String> workerWrapperT = new WorkerWrapper.Builder<String, String>()
                .worker(t).callback(t).param("t").next(workerWrapper1).build();

        long now = SystemClock.now();
        System.out.println("begin-" + now);

        Async.beginWork(5000, workerWrapperT);

        System.out.println("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        Async.shutDown();
    }

}
