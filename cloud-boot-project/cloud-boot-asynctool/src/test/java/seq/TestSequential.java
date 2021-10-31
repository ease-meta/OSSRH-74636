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

import com.open.cloud.async.executor.Async;
import com.open.cloud.async.executor.timer.SystemClock;
import com.open.cloud.async.wrapper.WorkerWrapper;

import java.util.concurrent.ExecutionException;

/**
 * 串行测试
 *
 * @author wuweifeng wrote on 2019-11-20.
 */
public class TestSequential {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        SeqWorker w = new SeqWorker();
        SeqWorker1 w1 = new SeqWorker1();
        SeqWorker2 w2 = new SeqWorker2();

        //顺序0-1-2
        WorkerWrapper<String, String> workerWrapper2 = new WorkerWrapper.Builder<String, String>()
                .worker(w2).callback(w2).param("2").build();

        WorkerWrapper<String, String> workerWrapper1 = new WorkerWrapper.Builder<String, String>()
                .worker(w1).callback(w1).param("1").next(workerWrapper2).build();

        WorkerWrapper<String, String> workerWrapper = new WorkerWrapper.Builder<String, String>()
                .worker(w).callback(w).param("0").next(workerWrapper1).build();

        //        testNormal(workerWrapper);

        testGroupTimeout(workerWrapper);
    }

    private static void testNormal(WorkerWrapper<String, String> workerWrapper)
            throws ExecutionException,
            InterruptedException {
        long now = SystemClock.now();
        System.out.println("begin-" + now);

        Async.beginWork(3500, workerWrapper);

        System.out.println("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        Async.shutDown();
    }

    private static void testGroupTimeout(WorkerWrapper<String, String> workerWrapper)
            throws ExecutionException,
            InterruptedException {
        long now = SystemClock.now();
        System.out.println("begin-" + now);

        Async.beginWork(2500, workerWrapper);

        System.out.println("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        Async.shutDown();
    }
}
