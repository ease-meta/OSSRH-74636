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
package depend;

import io.github.meta.ease.async.executor.Async;
import io.github.meta.ease.async.worker.WorkResult;
import io.github.meta.ease.async.wrapper.WorkerWrapper;

import java.util.concurrent.ExecutionException;

/**
 * 后面请求依赖于前面请求的执行结果
 *
 * @author wuweifeng wrote on 2019-12-26
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        DeWorker w = new DeWorker();
        DeWorker1 w1 = new DeWorker1();
        DeWorker2 w2 = new DeWorker2();

        WorkerWrapper<WorkResult<User>, String> workerWrapper2 = new WorkerWrapper.Builder<WorkResult<User>, String>()
                .worker(w2).callback(w2).id("third").build();

        WorkerWrapper<WorkResult<User>, User> workerWrapper1 = new WorkerWrapper.Builder<WorkResult<User>, User>()
                .worker(w1).callback(w1).id("second").next(workerWrapper2).build();

        WorkerWrapper<String, User> workerWrapper = new WorkerWrapper.Builder<String, User>()
                .worker(w).param("0").id("first").next(workerWrapper1, true).callback(w).build();

        //虽然尚未执行，但是也可以先取得结果的引用，作为下一个任务的入参。V1.2前写法，需要手工给
        //V1.3后，不用给wrapper setParam了，直接在worker的action里自行根据id获取即可.参考dependnew包下代码
        WorkResult<User> result = workerWrapper.getWorkResult();
        WorkResult<User> result1 = workerWrapper1.getWorkResult();
        workerWrapper1.setParam(result);
        workerWrapper2.setParam(result1);

        Async.beginWork(3500, workerWrapper);

        System.out.println(workerWrapper2.getWorkResult());
        Async.shutDown();
    }
}
